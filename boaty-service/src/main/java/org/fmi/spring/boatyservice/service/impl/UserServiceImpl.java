package org.fmi.spring.boatyservice.service.impl;

import org.fmi.spring.boatyservice.api.bindings.UserDetailsSpec;
import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.repository.UserRepository;
import org.fmi.spring.boatyservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
