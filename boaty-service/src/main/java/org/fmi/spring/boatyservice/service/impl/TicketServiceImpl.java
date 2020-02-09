package org.fmi.spring.boatyservice.service.impl;

import org.fmi.spring.boatyservice.model.Ticket;
import org.fmi.spring.boatyservice.repository.TicketRepository;
import org.fmi.spring.boatyservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

   @Autowired
   private TicketRepository repository;

   @Override
   public List<Ticket> getAllTickets() {
      return repository.findAll();
   }

   @Override
   public Ticket addTicket(Ticket t) {
      return repository.save(t);
   }
}
