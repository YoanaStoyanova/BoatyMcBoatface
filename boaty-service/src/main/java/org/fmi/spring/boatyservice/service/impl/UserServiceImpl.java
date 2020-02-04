package org.fmi.spring.boatyservice.service.impl;

import java.util.Arrays;

import org.fmi.spring.boatyservice.api.bindings.RegisterUserSpec;
import org.fmi.spring.boatyservice.api.bindings.UserDetailsSpec;
import org.fmi.spring.boatyservice.api.bindings.UserRoleSpec;
import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.model.UserRole;
import org.fmi.spring.boatyservice.repository.UserRepository;
import org.fmi.spring.boatyservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(RegisterUserSpec userSpec) {
        User user = new User();
        user.setUsername(userSpec.username);
        user.setPassword(passwordEncoder.encode(userSpec.password));
        if (!ObjectUtils.isEmpty(userSpec.firstName)) {
            user.setFirstName(userSpec.firstName);
        }
        if (!ObjectUtils.isEmpty(userSpec.lastName)) {
            user.setLastName(userSpec.lastName);
        }
        return userRepository.save(user);
    }

    @Override
    public Page<User> list(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public User loadById(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new ApplicationException(String.format("User with id %d does not exist", id), HttpStatus.NOT_FOUND));
    }

    @Override
    public User loadByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
            () -> new ApplicationException(String.format("User %s does not exist", username), HttpStatus.NOT_FOUND));
    }

    @Override
    public User updateUserDetails(Long id, UserDetailsSpec userSpec) {
        User user = loadById(id);
        if (!ObjectUtils.isEmpty(userSpec.firstName)) {
            user.setFirstName(userSpec.firstName);
        }
        if (!ObjectUtils.isEmpty(userSpec.lastName)) {
            user.setLastName(userSpec.lastName);
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUserRoles(Long id, UserRoleSpec userRoleSpec) {
        User user = loadById(id);
        UserRole newRole = Arrays.stream(UserRole.values())
            .filter(role -> role.name().equals(userRoleSpec.role))
            .findFirst()
            .orElseThrow(() -> {
                String errorMessage = String.format("Invalid role name %s", userRoleSpec.role);
                return new ApplicationException(errorMessage, HttpStatus.BAD_REQUEST);
            });
        user.setRole(newRole);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
