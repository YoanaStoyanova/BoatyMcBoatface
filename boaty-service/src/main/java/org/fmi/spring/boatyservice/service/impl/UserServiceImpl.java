package org.fmi.spring.boatyservice.service.impl;

import org.fmi.spring.boatyservice.api.bindings.CreateUserSpec;
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
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(CreateUserSpec userSpec) {
        User user = new User();
        user.setUsername(userSpec.username);
        user.setPassword(passwordEncoder.encode(userSpec.password));
        updateUserDetails(user, userSpec);
        return userRepository.save(user);
    }

    @Override
    public Page<User> listUsers(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new ApplicationException(String.format("User with id %d does not exist"), HttpStatus.NOT_FOUND));
    }

    @Override
    public User updateUser(Long id, CreateUserSpec userSpec) {
        User user = getUser(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private void updateUserDetails(User user, CreateUserSpec userSpec) {
        if (!ObjectUtils.isEmpty(userSpec.firstName)) {
            user.setFirstName(userSpec.firstName);
        }
        if (!ObjectUtils.isEmpty(userSpec.lastName)) {
            user.setLastName(userSpec.lastName);
        }
        if (!ObjectUtils.isEmpty(userSpec.role)) {
            user.setRole(UserRole.valueOf(userSpec.role));
        }
    }
}
