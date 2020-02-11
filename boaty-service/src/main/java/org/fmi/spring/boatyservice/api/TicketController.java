package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.api.bindings.TicketDetails;
import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.Ticket;
import org.fmi.spring.boatyservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.fmi.spring.boatyservice.api.bindings.TicketDetails.getTicketDetails;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

   @Autowired
   private TicketService ticketService;

   @GetMapping
   public List<TicketDetails> getAllTickets() {
      return ticketService.getAllTickets().stream().map(TicketDetails::getTicketDetails).collect(Collectors.toList());
   }

   @PostMapping
   public TicketDetails addTicket(@RequestBody Ticket t) {
      return getTicketDetails(ticketService.addTicket(t));
   }

   @PutMapping("/{ticket_id}")
   public TicketDetails updateTicket(@PathVariable Long ticketId, @RequestBody Ticket ticket) {
      if (ticketId.equals(ticket.getId())) {
         return getTicketDetails(ticketService.updateTicket(ticket));
      } else {
         throw new ApplicationException(
               String.format("Entity ID='%s' is different from URL resource ID='%s'", ticket.getId(), ticketId), HttpStatus.BAD_REQUEST);
      }
   }

   @DeleteMapping("/{ticket_id}")
   public TicketDetails deleteTicket(@PathVariable(name = "ticket_id") Long ticketId) {
      return getTicketDetails(ticketService.deleteTicket(ticketId));
   }


}
