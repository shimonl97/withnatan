package com.brahalla.Cerberus.controller.rest;

import com.brahalla.Cerberus.model.GoogleUserInfo;
import com.brahalla.Cerberus.model.base.BaseResponse;
import com.brahalla.Cerberus.model.base.BaseUrlResponse;
import com.brahalla.Cerberus.model.factory.CerberusUserFactory;
import com.brahalla.Cerberus.model.json.DigitsResponse;
import com.brahalla.Cerberus.model.json.request.AuthenticationRequest;
import com.brahalla.Cerberus.model.json.response.AuthenticationResponse;
import com.brahalla.Cerberus.model.security.CerberusUser;
import com.brahalla.Cerberus.model.user.User;
import com.brahalla.Cerberus.repository.UserRepository;
import com.brahalla.Cerberus.security.CerberusUserContext;
import com.brahalla.Cerberus.security.GoogleUserAuthentication;
import com.brahalla.Cerberus.security.TokenUtils;
import com.brahalla.Cerberus.security.TwitterAuthenticator;
import com.brahalla.Cerberus.service.ImageUploadService;
import com.brahalla.Cerberus.service.UserService;
import com.brahalla.Cerberus.service.impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("${cerberus.route.authentication}")
public class AuthenticationController {

    private final Logger log = Logger.getLogger(getClass());

    @Value("${cerberus.token.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TwitterAuthenticator userAuthenticationService;

    @Autowired
    private ImageUploadService imageUploadService;
    private ObjectMapper mapper = new ObjectMapper();
    @Value("${aws.bucket.url}")
    private String bucketUrl;

    @RequestMapping(path = "login",method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
        log.info("Authentication request has been made");
        log.info("User name: " + authenticationRequest.getUsername());
        log.info("Password: " + authenticationRequest.getPassword());
        // Perform the authentication
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest)
        );
        try {
            System.out.println(mapper.writeValueAsString(authenticationRequest));
        }catch (Exception e)
        {
            System.out.println("error parsing json");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticationResponse response = new AuthenticationResponse();
        // Reload password post-authentication so we can generate token
        try {
            log.info("Authentication user: " + authenticationRequest.getUsername());
            DigitsResponse digitsUser = userAuthenticationService.authenticate(authenticationRequest);
            if(digitsUser==null){
                return ResponseEntity.ok(new AuthenticationResponse("Failed End"));

            }
            CerberusUser userDetails;

                userDetails = userDetailsService.loadUserByUsername(digitsUser.getIdStr());
                log.info(userDetails.getUsername());
                if(userDetails.getName() == null){
                    response.setExists("false");
                }else {
                    response.setExists("true");
                    response.setImage(userDetails.getImage());
                    response.setName(userDetails.getName());
                    response.setNickName(userDetails.getNickName());
                    response.setUserId(userDetails.getId());
                }

            String token = this.tokenUtils.generateToken(userDetails, device);
            response.setToken(token);
            return ResponseEntity.ok(response);

        } catch (IOException | RuntimeException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.ok(new AuthenticationResponse("Failed End"));

        }
        // Return the token
    }
    @RequestMapping(path = "register",method = RequestMethod.POST)
    public ResponseEntity<?> registerRequest(@RequestBody AuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
        String userId = CerberusUserContext.currentUserDetails().getId();

        AuthenticationResponse response = new AuthenticationResponse();
        // Reload password post-authentication so we can generate token
        try {

            User currentUser = userRepository.findOne(userId);
            log.info(currentUser.getPhone());
            currentUser.setName(authenticationRequest.getName());
            currentUser.setNickName(authenticationRequest.getNickName());
            currentUser.setPhoto(userId+".png");
            boolean success = imageUploadService.UploadObjectSingleOperation("users/"+userId+".png",authenticationRequest.getPhoto());
                System.out.println(success);
            userRepository.save(currentUser);

            response.setImage(currentUser.getPhoto());
            response.setName(currentUser.getName());
            response.setNickName(currentUser.getNickName());
            response.setUserId(currentUser.getId());

            return ResponseEntity.ok(response);

        } catch ( RuntimeException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.ok(new AuthenticationResponse("Failed End"));

        }
        // Return the token
    }
    @RequestMapping(value = "${cerberus.route.authentication.base}", method = RequestMethod.GET)
    public ResponseEntity<?> getBase(HttpServletRequest request) {

        return ResponseEntity.ok(new BaseUrlResponse(bucketUrl));
    }
    @RequestMapping(value = "${cerberus.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
        String token = request.getHeader(this.tokenHeader);
        String username = this.tokenUtils.getUsernameFromToken(token);
        CerberusUser user = (CerberusUser) userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, null)) {
            String refreshedToken = this.tokenUtils.refreshToken(token);
            return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
