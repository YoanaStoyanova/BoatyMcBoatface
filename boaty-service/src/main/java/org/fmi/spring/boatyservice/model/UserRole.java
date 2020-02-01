package org.fmi.spring.boatyservice.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN,
    USER,
    // Validates user's paid tickets and parking
    INSPECTOR;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
