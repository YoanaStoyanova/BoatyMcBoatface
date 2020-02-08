package org.fmi.spring.boatyservice.repository;

import org.fmi.spring.boatyservice.model.CardPaymentMethod;
import org.fmi.spring.boatyservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardPaymentMethodRepository extends JpaRepository<CardPaymentMethod, Long> {

    Page<CardPaymentMethod> findAllByUser(User user, Pageable pageable);

}
