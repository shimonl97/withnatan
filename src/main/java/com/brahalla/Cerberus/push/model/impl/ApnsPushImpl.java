package com.brahalla.Cerberus.push.model.impl;

import com.brahalla.Cerberus.push.model.ApnsPush;
import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.Payload;
import javapns.notification.PushNotificationPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Apns Push Implementation
 */
@Component
public class ApnsPushImpl implements ApnsPush {
    @Value("${push.apnspath}")
    private String APNS_PRD_CERT_PATH;
    @Value("${push.apnspassword}")
    private String APNS_PRD_PASSWORD;

    @Autowired
    private ResourceLoader resourceLoader;

    private PushNotificationPayload payload = new PushNotificationPayload();

    @Override
    public void badge(int badgeCount, String keystore, String password, boolean isProduction, List<String> deviceTokenList) throws CommunicationException, KeystoreException {
        Push.badge(badgeCount, keystore, password, isProduction, deviceTokenList);
    }

    public void sendPayload(Payload payload, List<String> deviceTokens) throws CommunicationException, KeystoreException {
        Push.payload(payload, APNS_PRD_CERT_PATH, APNS_PRD_PASSWORD, true, deviceTokens);
    }

}
