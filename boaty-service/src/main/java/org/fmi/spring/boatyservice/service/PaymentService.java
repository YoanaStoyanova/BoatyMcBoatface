package org.fmi.spring.boatyservice.service;

public interface PaymentService {

    void performPayment(Long paymentMethodId, Double amount);

    String supportedPaymentType();

}
