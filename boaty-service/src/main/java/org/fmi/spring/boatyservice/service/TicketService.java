package org.fmi.spring.boatyservice.service;

import org.fmi.spring.boatyservice.model.Ticket;

import java.util.List;

public interface TicketService {

   public List<Ticket> getAllTickets();

   public Ticket addTicket(Ticket t);
}
