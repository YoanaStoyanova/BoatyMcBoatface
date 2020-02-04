package org.fmi.spring.boatyservice.security.token;

public class AuthToken {

    private String token;
    private long expiresAt;

    public AuthToken(String token, long expiresAt) {
        this.token = token;
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

}
