package org.fmi.spring.boatyservice.security.authentication;

import org.springframework.security.core.Authentication;

public interface AuthenticationManager {

    Authentication getAuthentication(String authClaim);
}
