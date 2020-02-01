package org.fmi.spring.boatyservice.service;

import org.fmi.spring.boatyservice.api.bindings.CreateUserSpec;
import org.fmi.spring.boatyservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createUser(CreateUserSpec userSpec);

    Page<User> listUsers(Pageable pageRequest);

    User getUser(Long id);

    User updateUser(Long id, CreateUserSpec userSpec);

    void deleteUser(Long userId);
}
