package org.fmi.spring.boatyservice.security.authentication;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAuthDetailsService extends UserDetailsService {

    UserAuthDetails updateUserRoles(Long id, UserRole role);
}
