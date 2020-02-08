package org.fmi.spring.boatyservice.api.bindings;

import java.time.YearMonth;

import org.fmi.spring.boatyservice.model.CardPaymentMethod;

public class CardPaymentDetails {

    public Long cardNumber;
    public String cardHolder;
    public YearMonth validity;

    public CardPaymentDetails(CardPaymentMethod card) {
        this.cardHolder = card.getCardHolder();
        this.cardNumber = card.getCardNumber();
        this.validity = card.getValidity();
    }

}
