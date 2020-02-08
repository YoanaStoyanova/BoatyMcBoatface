package org.fmi.spring.boatyservice.security.authentication;

import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthDetailsServiceImpl implements UserAuthDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s does not exist", username)));
    }

    @Override
    public UserAuthDetails updateUserRoles(Long id, UserRole role) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ApplicationException(String.format("User with id %d does not exist", id), HttpStatus.NOT_FOUND));
        user.setRole(role);
        return userRepository.save(user);
    }
}
