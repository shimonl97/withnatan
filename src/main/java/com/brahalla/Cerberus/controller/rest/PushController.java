package com.brahalla.Cerberus.controller.rest;

import com.brahalla.Cerberus.model.json.request.SetPushTokenRequest;
import com.brahalla.Cerberus.model.user.User;
import com.brahalla.Cerberus.repository.UserRepository;
import com.brahalla.Cerberus.security.CerberusUserContext;
import com.brahalla.Cerberus.security.TokenUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.MultiServerUserRegistry;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("${cerberus.route.push}")
public class PushController {

    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;






    @RequestMapping(path = "setGCMToken", method = RequestMethod.POST)
    public ResponseEntity<?> setGCMToken(@RequestBody SetPushTokenRequest request) {
        User user = userRepository.findOne(CerberusUserContext.currentUserDetails().getId());
        user.setGcmToken(request.getPushToken());
        user.setGcmTokenLastUpdate(new Date());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "setAPNToken", method = RequestMethod.POST)
    public ResponseEntity<?> setAPNToken(@RequestBody SetPushTokenRequest request) {
        User user = userRepository.findOne(CerberusUserContext.currentUserDetails().getId());
        user.setApnToken(request.getPushToken());
        user.setApnTokenLastUpdate(new Date());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }



}
