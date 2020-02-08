package org.fmi.spring.boatyservice.service.impl;

import org.fmi.spring.boatyservice.api.bindings.RegisterUserSpec;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.repository.UserRepository;
import org.fmi.spring.boatyservice.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

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
}
