package org.fmi.spring.boatyservice.api;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.fmi.spring.boatyservice.api.bindings.CardPaymentDetails;
import org.fmi.spring.boatyservice.api.bindings.CardSpec;
import org.fmi.spring.boatyservice.api.bindings.PagedResponse;
import org.fmi.spring.boatyservice.model.CardPaymentMethod;
import org.fmi.spring.boatyservice.service.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class CardDetailsOps {

    private static final DateTimeFormatter CARD_VALIDITY_FORMAT = DateTimeFormatter.ofPattern("M/yy");

    @Autowired
    private PaymentDetailsService paymentDetailsService;

    @GetMapping("/{user_id}/cards")
    PagedResponse<CardPaymentDetails> listCards(@PathVariable(name = "user_id") long userId,
        @RequestParam(name="page", defaultValue = "0") int page,
        @RequestParam(name="size", defaultValue = "20") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<CardPaymentDetails> cardsPage = paymentDetailsService.listPaymentMethods(userId, pageRequest)
            .map(CardPaymentDetails::new);
        return new PagedResponse<>(cardsPage);
    }

    @PostMapping("/{user_id}/cards")
    CardPaymentDetails registerCardPaymentMethod(
        @PathVariable(name = "user_id") long userId,
        @RequestBody CardSpec cardSpec) {

        YearMonth validity = cardSpec.getValidity(CARD_VALIDITY_FORMAT);
        CardPaymentMethod card = new CardPaymentMethod(cardSpec.cardHolder, cardSpec.cardNumber, validity);
        return new CardPaymentDetails(paymentDetailsService.registerPaymentMethod(userId, card));
    }

    @DeleteMapping("/cards/{card_id}")
    void deletePaymentMethod(@PathVariable(name = "card_id") long cardId) {
        paymentDetailsService.deletePaymentMethod(cardId);
    }

}
