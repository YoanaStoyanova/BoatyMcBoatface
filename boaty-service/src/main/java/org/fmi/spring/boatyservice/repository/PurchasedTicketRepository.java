package org.fmi.spring.boatyservice.repository;

import java.util.List;

import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.model.UserPurchasedTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedTicketRepository extends JpaRepository<UserPurchasedTickets, Long> {

    List<UserPurchasedTickets> findAllByUser(User user);

}
