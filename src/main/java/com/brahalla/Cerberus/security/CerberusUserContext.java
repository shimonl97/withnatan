package com.brahalla.Cerberus.security;

import com.brahalla.Cerberus.model.security.CerberusUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by kfirf on 9/16/16.
 */
public class CerberusUserContext {

    public static CerberusUser currentUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            return principal instanceof CerberusUser ? (CerberusUser) principal : null;
        }
        return null;
    }
}
