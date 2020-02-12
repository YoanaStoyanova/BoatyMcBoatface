package org.fmi.spring.boatyservice.service.impl;

import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.Ticket;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.model.UserPurchasedTickets;
import org.fmi.spring.boatyservice.repository.PurchasedTicketRepository;
import org.fmi.spring.boatyservice.repository.TicketRepository;
import org.fmi.spring.boatyservice.repository.UserRepository;
import org.fmi.spring.boatyservice.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TicketPurchaseService implements PurchaseService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchasedTicketRepository purchasedTicketRepository;

    @Override
    public void purchase(Long userId, Long itemId) {
        User user = userRepository.findById(userId).orElseThrow(
            () -> new ApplicationException(String.format("User with id %d does not exist", userId), HttpStatus.NOT_FOUND));

        Ticket ticket = ticketRepository.findById(itemId).orElseThrow(
            () -> new ApplicationException(String.format("Ticket with id %d does not exist", userId), HttpStatus.NOT_FOUND));

        if (user.getAccountBalance() < ticket.getPrice()) {
            throw new ApplicationException("Insufficient funds", HttpStatus.BAD_REQUEST);
        }

        user.setAccountBalance(user.getAccountBalance() - ticket.getPrice());
        userRepository.save(user);
        purchasedTicketRepository.save(new UserPurchasedTickets(user, ticket));
    }
}
