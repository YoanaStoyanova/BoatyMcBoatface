package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.model.Ticket;
import org.fmi.spring.boatyservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

   @Autowired
   private TicketService ticketService;

   @GetMapping
   public List<Ticket> getAllTickets() {
      return ticketService.getAllTickets();
   }

   @PostMapping
   public Ticket addTicket(@RequestBody Ticket t) {
      return ticketService.addTicket(t);
   }

}
