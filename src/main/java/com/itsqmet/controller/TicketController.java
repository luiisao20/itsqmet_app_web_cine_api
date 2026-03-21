package com.itsqmet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsqmet.dto.TicketDTO;
import com.itsqmet.service.TicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/tickets")
public class TicketController {
  @Autowired
  private TicketService ticketService;

  @PostMapping("/save")
  public TicketDTO saveTicket(@RequestBody TicketDTO ticket) {
    return ticketService.saveTicket(ticket);
  }

  @GetMapping("/{id}")
  public Optional<TicketDTO> getTicketById(@PathVariable Long id) {
    return ticketService.getTicketById(id);
  }

  @GetMapping("/schedule/{id}")
  public List<TicketDTO> getByMovie(@PathVariable Long id) {
    return ticketService.getTicketsBySchedule(id);
  }

  @GetMapping("/user/{id}")
  public List<TicketDTO> getByMovie(@PathVariable String id) {
    return ticketService.getTicketsByUser(id);
  }

}
