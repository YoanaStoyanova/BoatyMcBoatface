package org.fmi.spring.boatyservice.security.authentication;

import org.springframework.security.core.context.SecurityContextHolder;

public final class AuthUtil {

    public static String currentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
