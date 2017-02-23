package com.brahalla.Cerberus.api;

import com.brahalla.Cerberus.model.json.request.CreateGroupRequest;
import com.brahalla.Cerberus.model.security.CerberusUser;
import com.brahalla.Cerberus.security.TokenUtils;
import com.brahalla.Cerberus.utils.json.JacksonConverter;
import com.brahalla.Cerberus.utils.json.JsonConverter;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DevicePlatform;
import org.springframework.mobile.device.DeviceType;
import org.springframework.mobile.device.DeviceUtils;

/**
 * Created by mymac on 11/11/2016.
 */
public class GenerateToken {
    private static JsonConverter jsonConverter = new JacksonConverter();
    private static TokenUtils tokenUtils = new TokenUtils();
    public static void main(String[] args) throws Exception {
        CerberusUser userDetails = new CerberusUser();
        userDetails.setId("781050160499613696");
        userDetails.setUsername("781050160499613696");
        String token = tokenUtils.generateToken(userDetails, new Device() {
            @Override
            public boolean isNormal() {
                return true;
            }

            @Override
            public boolean isMobile() {
                return false;
            }

            @Override
            public boolean isTablet() {
                return false;
            }

            @Override
            public DevicePlatform getDevicePlatform() {
                return null;
            }
        });

        System.out.println(token);
    }
}

