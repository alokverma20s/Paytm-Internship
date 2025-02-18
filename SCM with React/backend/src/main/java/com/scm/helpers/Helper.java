package com.scm.helpers;

import org.springframework.security.core.Authentication;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {
        if (authentication == null) {
            throw new IllegalStateException("Authentication object is null");
        }
        return authentication.getName();
    }
}
