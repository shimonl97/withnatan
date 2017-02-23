package com.brahalla.Cerberus.model.factory;

import com.brahalla.Cerberus.model.security.CerberusUser;
import com.brahalla.Cerberus.model.user.User;

public class CerberusUserFactory {

    public static CerberusUser create(User user) {
        CerberusUser cerberusUser = new CerberusUser(
                user.getId(),
                user.getId(),
                user.getPhone(),
                user.getPhone(),
                null
        );
         cerberusUser.setName(user.getName());
        cerberusUser.setNickName(user.getNickName());
        cerberusUser.setImage(user.getPhoto());
        return cerberusUser;
    }

}
