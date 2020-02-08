package org.fmi.spring.boatyservice.test;

import org.fmi.spring.boatyservice.model.CardPaymentMethod;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.repository.CardPaymentMethodRepository;
import org.fmi.spring.boatyservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public final class TestUtils {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardPaymentMethodRepository paymentMethodRepository;


    public User createUser(String username, String password) {
        return createUser(username, password, null);
    }

    public User createUser(String username, String password, CardPaymentMethod paymentMethod) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = userRepository.save(user);

        if (paymentMethod != null) {
            paymentMethod.setUser(user);
            paymentMethodRepository.save(paymentMethod);
        }
        return user;
    }
}
