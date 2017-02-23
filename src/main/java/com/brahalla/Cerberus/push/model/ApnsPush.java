package com.brahalla.Cerberus.push.model;

import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;

import java.util.List;

/**
 * Apns Push Interface
 */
public interface ApnsPush {

    /**
     * Send Badge
     *
     * @param badgeCount
     * @param keystore
     * @param password
     * @param isProduction
     * @param deviceTokenList
     * @return ApnsPushNotifications
     * @throws CommunicationException
     * @throws KeystoreException
     */
    public void badge(int badgeCount, String keystore, String password, boolean isProduction, List<String> deviceTokenList) throws CommunicationException, KeystoreException;

}
