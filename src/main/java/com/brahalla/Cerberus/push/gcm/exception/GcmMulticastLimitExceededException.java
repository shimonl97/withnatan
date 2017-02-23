package com.brahalla.Cerberus.push.gcm.exception;

/**
 * GCM_MULTICAST_LIMIT_EXCEED_EXCEPTION <br /> <br />
 * <p>
 * This Exception occurs when regList size exceeds 1000 since limit of max regList is 1000 <br />
 * according to <a href="https://developer.android.com/google/gcm/server.html#send-msg">https://developer.android.com/google/gcm/server.html#send-msg</a>.
 */
public class GcmMulticastLimitExceededException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6381148606480403600L;

}
