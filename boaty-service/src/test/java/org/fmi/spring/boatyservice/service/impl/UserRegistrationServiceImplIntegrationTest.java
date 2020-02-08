package org.fmi.spring.boatyservice.service.impl;

import java.util.UUID;

import org.fmi.spring.boatyservice.api.bindings.RegisterUserSpec;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.security.authentication.UserRole;
import org.fmi.spring.boatyservice.service.UserRegistrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations ="classpath:application-test.properties")
@ContextConfiguration(classes = UserRegistrationServiceImplIntegrationTest.Config.class)
public class UserRegistrationServiceImplIntegrationTest {

    @TestConfiguration
    @EnableAutoConfiguration
    @EntityScan(basePackages = "org.fmi.spring.boatyservice.model")
    @EnableJpaRepositories(basePackages = "org.fmi.spring.boatyservice.repository")
    public static class Config {

        @Bean
        public UserRegistrationService userRegistrationService() {
            return new UserRegistrationServiceImpl();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }
    }

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Test
    void testCanRegisterNewUser() {
        RegisterUserSpec registerUserSpec = new RegisterUserSpec();
        registerUserSpec.firstName = "John";
        registerUserSpec.lastName = "Doe";
        registerUserSpec.username = String.format("jdoe-%s@mail.com", UUID.randomUUID().toString());
        registerUserSpec.password = "test";
        User user = userRegistrationService.register(registerUserSpec);

        assertEquals(registerUserSpec.firstName, user.getFirstName());
        assertEquals(registerUserSpec.lastName, user.getLastName());
        assertEquals(registerUserSpec.username, user.getUsername());
        assertEquals(registerUserSpec.password, user.getPassword());
        assertEquals(UserRole.USER, user.getRole());
    }
}
