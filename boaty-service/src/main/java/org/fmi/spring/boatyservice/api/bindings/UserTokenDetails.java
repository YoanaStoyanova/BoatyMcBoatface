package org.fmi.spring.boatyservice.api.bindings;

public class UserTokenDetails {
    public String accessToken;
    public long expireTime;

    public UserTokenDetails(String accessToken, long expireTime) {
        this.accessToken = accessToken;
        this.expireTime = expireTime;
    }
}
