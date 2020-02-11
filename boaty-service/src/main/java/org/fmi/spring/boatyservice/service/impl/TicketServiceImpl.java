package org.fmi.spring.boatyservice.service.impl;

import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.Ticket;
import org.fmi.spring.boatyservice.repository.TicketRepository;
import org.fmi.spring.boatyservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

   private Ticket loadById(Long id) {
      return repository.findById(id).orElseThrow(
            () -> new ApplicationException(String.format("Ticket with id %d does not exist", id), HttpStatus.NOT_FOUND));
   }

   @Override
   public Ticket updateTicket(Ticket t) {
      Ticket ticket = loadById(t.getId());
      return repository.save(t);
   }

   @Override
   public Ticket deleteTicket(Long id) {
      Ticket oldTicket = loadById(id);
      repository.deleteById(id);
      return oldTicket;
   }
}
