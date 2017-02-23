package com.brahalla.Cerberus.push.gcm.model;

import com.brahalla.Cerberus.push.gcm.exception.GcmMulticastLimitExceededException;
import com.brahalla.Cerberus.push.gcm.pushraven.FcmResponse;
import com.brahalla.Cerberus.push.gcm.vo.GcmPushInfo;

import java.io.IOException;


public interface GcmPush {
    /**
     * @param {@link GcmPushInfo}
     * @throws GcmMulticastLimitExceededException
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public FcmResponse sendPush(GcmPushInfo info) throws GcmMulticastLimitExceededException, IllegalArgumentException, IOException;
}
