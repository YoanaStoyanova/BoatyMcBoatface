package org.fmi.spring.boatyservice.security.token;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public interface TokenProvider {

    AuthToken createToken(String username, Collection<? extends GrantedAuthority> authorities);
}
