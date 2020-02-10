package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.Ticket;
import org.fmi.spring.boatyservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

   @PutMapping("/{ticket_id}")
   public Ticket updateTicket(@PathVariable Long ticketId, @RequestBody Ticket ticket) {
      if (ticketId.equals(ticket.getId())) {
         return ticketService.updateTicket(ticket);
      } else {
         throw new ApplicationException(
               String.format("Entity ID='%s' is different from URL resource ID='%s'", ticket.getId(), ticketId), HttpStatus.BAD_REQUEST);
      }
   }


}
