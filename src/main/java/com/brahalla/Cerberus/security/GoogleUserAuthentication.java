package com.brahalla.Cerberus.security;

import com.brahalla.Cerberus.model.GoogleUserInfo;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/**
 * Created by Dani Goland on 08/03/2016.
 */
@Component
public class GoogleUserAuthentication {

    private final Logger log = Logger.getLogger(getClass());

    @Value("${cerberus.google.client.id.web}")
    private String CLIENT_ID_WEB;
    @Value("${cerberus.google.client.id.android}")
    private String CLIENT_ID_ANDROID_SDK;

    private GoogleIdTokenVerifier verifier = null;

    @PostConstruct
    private void buildGoogleTokenVerifier() throws GeneralSecurityException, IOException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Arrays.asList(CLIENT_ID_WEB, CLIENT_ID_WEB))
                .setIssuer("accounts.google.com").build();
    }


    public GoogleUserInfo getGoogleUser(String googleIdToken) throws GeneralSecurityException, IOException {
        GoogleUserInfo userInfo = new GoogleUserInfo();

        try {
            GoogleIdToken idToken = verifier.verify(googleIdToken);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                // Print user identifier
                userInfo.setUserId(payload.getSubject());
                log.info("User ID: " + userInfo.getUserId());

                // Get profile information from payload
                userInfo.setEmail(payload.getEmail());
                userInfo.setEmailVerified(payload.getEmailVerified());
                userInfo.setName((String) payload.get("name"));
                userInfo.setPictureUrl((String) payload.get("picture"));
                userInfo.setLocale((String) payload.get("locale"));
                userInfo.setFamilyName((String) payload.get("family_name"));
                userInfo.setGivenName((String) payload.get("given_name"));
                userInfo.setValidToken(true);
                return userInfo;
                // Use or store profile information
                // ...

            } else {
                userInfo.setValidToken(false);
                log.info("Invalid ID token.");
                return userInfo;
            }
        } catch (IOException e) {
            throw e;
        } catch (GeneralSecurityException e) {
            throw e;
        }
    }
}