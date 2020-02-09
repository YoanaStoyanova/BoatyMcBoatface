package org.fmi.spring.boatyservice.repository;

import org.fmi.spring.boatyservice.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
