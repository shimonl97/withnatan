package com.brahalla.Cerberus.configuration;

import com.brahalla.Cerberus.model.GoogleUserInfo;
import com.brahalla.Cerberus.model.json.DigitsResponse;
import com.brahalla.Cerberus.model.json.request.AuthenticationRequest;
import com.brahalla.Cerberus.model.security.CerberusUser;
import com.brahalla.Cerberus.model.user.User;
import com.brahalla.Cerberus.security.GoogleUserAuthentication;
import com.brahalla.Cerberus.security.TwitterAuthenticator;
import com.brahalla.Cerberus.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 0503337710 on 11/09/2016.
 */
@Component
public class LocalAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TwitterAuthenticator twitterAuthenticator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
    }

    @Override
    public UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        DigitsResponse digitsResponse;
        AuthenticationRequest request = (AuthenticationRequest) authentication.getCredentials();

        try {
            log.info("Retrieve user: " + username);


            digitsResponse = twitterAuthenticator.authenticate(new AuthenticationRequest(username,request.getPassword(),null,null,null));
            if(digitsResponse==null){
                throw new BadCredentialsException("Invalid Login");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BadCredentialsException("Invalid Login");
        }
        User user = userService.getById(digitsResponse.getIdStr());
        if (user == null) {
             user = userService.create(new User(digitsResponse.getIdStr(), digitsResponse.getPhoneNumber(), request.getName(), request.getPhoto(),request.getNickName()));
        }
        final List<GrantedAuthority> auths;
        auths = AuthorityUtils.NO_AUTHORITIES;
        return new CerberusUser(user.getId(), user.getId(), user.getName(), user.getPhone(), auths);
    }


}
