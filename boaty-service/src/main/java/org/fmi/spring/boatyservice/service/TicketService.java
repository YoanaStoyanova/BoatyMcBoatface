package org.fmi.spring.boatyservice.service;

import org.fmi.spring.boatyservice.model.Ticket;

import java.util.List;

public interface TicketService {

   List<Ticket> getAllTickets();

   Ticket addTicket(Ticket t);

   Ticket updateTicket(Ticket t);
}
