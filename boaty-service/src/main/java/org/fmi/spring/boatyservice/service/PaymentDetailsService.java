package org.fmi.spring.boatyservice.service;

import org.fmi.spring.boatyservice.model.CardPaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentDetailsService {

    CardPaymentMethod registerPaymentMethod(Long userId, CardPaymentMethod paymentSpec);

    Page<CardPaymentMethod> listPaymentMethods(Long userId, Pageable pageable);

    void deletePaymentMethod(Long id);
}
