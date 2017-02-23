package com.brahalla.Cerberus.push.gcm.model.impl;

import com.brahalla.Cerberus.push.gcm.exception.GcmMulticastLimitExceededException;
import com.brahalla.Cerberus.push.gcm.model.GcmPush;
import com.brahalla.Cerberus.push.gcm.pushraven.FcmResponse;
import com.brahalla.Cerberus.push.gcm.pushraven.Pushraven;
import com.brahalla.Cerberus.push.gcm.vo.GcmPushInfo;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Gcm Push Implementation.
 */
@Component
public class GcmPushImpl implements GcmPush {


    private static final int GCM_MULTICAST_LIMIT = 1000;

    @Value("${push.gcmtoken}")
    private String GCM_API_KEY;

    private Pushraven sender;

    @PostConstruct
    public void postConstruct() {
        sender  = new Pushraven(GCM_API_KEY);
    }

    @Override
    public FcmResponse sendPush(GcmPushInfo info) throws GcmMulticastLimitExceededException, IllegalArgumentException, IOException {
        // Validation
        Assert.notNull(info, "info should not be null.");
        Assert.isTrue(info.getData() != null , "data should not be Null or empty string.");
        Assert.isTrue(info.getRegIdList() != null && info.getRegIdList().size() > 0, "regIdList should not be Null or empty string.");
        if (info.getRegIdList().size() > GCM_MULTICAST_LIMIT) {
            throw new GcmMulticastLimitExceededException();
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("data",info.getData());
        sender.addAllMulticasts(info.getRegIdList())
                .data(map);
        FcmResponse response = sender.push();
        sender.clear();


        return response;
    }
}
