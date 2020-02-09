package org.fmi.spring.boatyservice.service.impl;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.CardPaymentMethod;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.repository.CardPaymentMethodRepository;
import org.fmi.spring.boatyservice.repository.UserRepository;
import org.fmi.spring.boatyservice.service.PaymentService;
import org.fmi.spring.boatyservice.service.UserService;
import org.fmi.spring.boatyservice.test.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations ="classpath:application-test.properties")
@ContextConfiguration(classes = CardPaymentDetailsServiceIntegrationTest.Config.class)
public class CardPaymentDetailsServiceIntegrationTest {

    @TestConfiguration
    @EnableAutoConfiguration
    @EntityScan(basePackages = "org.fmi.spring.boatyservice.model")
    @EnableJpaRepositories(basePackages = "org.fmi.spring.boatyservice.repository")
    public static class Config {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }

        @Bean
        public CardPaymentDetailsService cardPaymentDetailsService() {
            return new CardPaymentDetailsService();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

        @Bean
        public PaymentService paymentService() {
            return new CardPaymentService();
        }

        @Bean
        public TestUtils testUtils() {
            return new TestUtils();
        }
    }

    @Autowired
    private CardPaymentDetailsService cardPaymentDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardPaymentMethodRepository paymentMethodRepository;

    @Autowired
    private TestUtils testUtils;

    private User user;
    private CardPaymentMethod paymentMethod;

    @BeforeEach
    void setUp() {
        YearMonth current = YearMonth.now();
        YearMonth nextYear = YearMonth.of(current.getYear() + 1, current.getMonth());
        paymentMethod = new CardPaymentMethod("John Doe", 4737503036444300L, nextYear);
        user = testUtils.createUser("test-" + UUID.randomUUID().toString(), "password");
    }

    @AfterEach
    void tearDown() {
        userRepository.delete(user);
    }

    @Test
    void testCreateCardPayment() {
        paymentMethod = cardPaymentDetailsService.registerPaymentMethod(user.getId(), paymentMethod);
        assertEquals(user.getId(), paymentMethod.getUser().getId());
    }

    @Test
    void testDeleteUserDeletesPaymentMethods() {
        cardPaymentDetailsService.registerPaymentMethod(user.getId(), paymentMethod);
        userRepository.delete(user);
        assertFalse(paymentMethodRepository.findAll().stream().anyMatch(p -> p.getUser().getId().equals(user.getId())));
    }

    @Test
    void testRegisterCardWithInvalidNumberFails() {
        paymentMethod.setCardNumber(123L);
        assertThrows(ApplicationException.class, () -> cardPaymentDetailsService.registerPaymentMethod(user.getId(), paymentMethod));
    }

    @Test
    void testUnableToRegisterCardForMissingUser() {
        assertThrows(ApplicationException.class, () -> cardPaymentDetailsService.registerPaymentMethod(123123L, paymentMethod));
    }

    @Test
    void testRegisterSameCardTwice() {
        cardPaymentDetailsService.registerPaymentMethod(user.getId(), paymentMethod);
        assertThrows(ApplicationException.class, () -> cardPaymentDetailsService.registerPaymentMethod(user.getId(), paymentMethod));
    }

    @Test
    void registerExpiredCardFails() {
        YearMonth lastyear = YearMonth.of(YearMonth.now().getYear() - 1, 10);
        paymentMethod.setValidity(lastyear);
        assertThrows(ApplicationException.class, () -> cardPaymentDetailsService.registerPaymentMethod(user.getId(), paymentMethod));
    }

    @Test
    void testRegisterMultipleCards() {
        cardPaymentDetailsService.registerPaymentMethod(user.getId(), paymentMethod);

        paymentMethod.setId(null);
        paymentMethod.setCardNumber(5510244076285069L);
        cardPaymentDetailsService.registerPaymentMethod(user.getId(), paymentMethod);

        paymentMethod.setId(null);
        paymentMethod.setCardNumber(5165984467611062L);
        cardPaymentDetailsService.registerPaymentMethod(user.getId(), paymentMethod);

        Page<CardPaymentMethod> cards = cardPaymentDetailsService.listPaymentMethods(user.getId(), PageRequest.of(0, 10));

        assertEquals(3, cards.getTotalElements());
        List<Long> actualNumbers = cards.map(CardPaymentMethod::getCardNumber).getContent();
        assertTrue(actualNumbers.containsAll(Arrays.asList(5165984467611062L, 5510244076285069L, 4737503036444300L)));
    }
}
