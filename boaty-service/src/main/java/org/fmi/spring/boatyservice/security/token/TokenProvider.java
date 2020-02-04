package org.fmi.spring.boatyservice.security.token;

public interface TokenProvider {

    AuthToken createToken(String username);
}
