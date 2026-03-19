package com.itsqmet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.entity.Ticket;
import com.itsqmet.repository.TicketRepository;

@Service
public class TicketService {
  @Autowired
  private TicketRepository ticketRepository;

  public Ticket saveTicket(Ticket ticket) {
    return ticketRepository.save(ticket);
  }

  public Optional<Ticket> getTicketById(Long id) {
    return ticketRepository.findById(id);
  }
}
