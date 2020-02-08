package org.fmi.spring.boatyservice.service;

import org.fmi.spring.boatyservice.api.bindings.RegisterUserSpec;
import org.fmi.spring.boatyservice.api.bindings.UserDetailsSpec;
import org.fmi.spring.boatyservice.api.bindings.UserRoleSpec;
import org.fmi.spring.boatyservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> list(Pageable pageRequest);

    User loadById(Long id);

    User updateUserDetails(Long id, UserDetailsSpec userSpec);

    User updateUserRoles(Long id, UserRoleSpec userRoleSpec);

    void delete(Long userId);
}
