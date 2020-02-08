package org.fmi.spring.boatyservice.service.impl;

import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentService implements PaymentService {

    @Autowired
    private CardPaymentDetailsService cardPaymentDetailsService;

    @Override
    public void performPayment(Long cardId, Double amount) {
        if (amount <= 0) {
            String error = String.format("Cannot perform a payment on 0 or less, %d given", amount);
            throw new ApplicationException(error, HttpStatus.BAD_REQUEST);
        }

        // TODO: Do something with card
        cardPaymentDetailsService.getPaymentDetails(cardId);
    }

    @Override
    public String supportedPaymentType() {
        return "CARD";
    }
}
