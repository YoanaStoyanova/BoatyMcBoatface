package org.fmi.spring.boatyservice.service.impl;

import java.time.YearMonth;

import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.CardPaymentMethod;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.repository.CardPaymentMethodRepository;
import org.fmi.spring.boatyservice.service.PaymentDetailsService;
import org.fmi.spring.boatyservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentDetailsService implements PaymentDetailsService {

    @Autowired
    private CardPaymentMethodRepository paymentMethodRepository;
    @Autowired
    private UserService userService;

    @Override
    public CardPaymentMethod getPaymentDetails(Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId)
            .orElseThrow(() -> {
                String error = String.format("Card [%d] does not exist", paymentMethodId);
                return new ApplicationException(error, HttpStatus.NOT_FOUND);
            });
    }

    @Override
    public CardPaymentMethod registerPaymentMethod(Long userId, CardPaymentMethod paymentMethod) {
        paymentMethod.setUser(userService.loadById(userId));
        if (!CardUtils.cardNumberIsValid(paymentMethod.getCardNumber())) {
            String error = String.format("Invalid card number %d", paymentMethod.getCardNumber());
            throw new ApplicationException(error, HttpStatus.BAD_REQUEST);
        }
        if (YearMonth.now().isAfter(paymentMethod.getValidity())) {
            throw new ApplicationException("Card is expired", HttpStatus.BAD_REQUEST);
        }
        if (paymentMethodRepository.findOne(Example.of(paymentMethod)).isPresent()) {
            throw new ApplicationException("Card already register to this user", HttpStatus.BAD_REQUEST);
        }
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public Page<CardPaymentMethod> listPaymentMethods(Long userId, Pageable pageable) {
        User user = userService.loadById(userId);
        return paymentMethodRepository.findAllByUser(user, pageable);
    }

    @Override
    public void deletePaymentMethod(Long id) {
        if (paymentMethodRepository.existsById(id)) {
            paymentMethodRepository.deleteById(id);
        } else {
            throw new ApplicationException(String.format("Card with id %d not found", id), HttpStatus.NOT_FOUND);
        }
    }
}
