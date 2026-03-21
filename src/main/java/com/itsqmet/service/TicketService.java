package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.TicketDTO;
import com.itsqmet.entity.Ticket;
import com.itsqmet.repository.TicketRepository;

@Service
public class TicketService {
  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private ScheduleService scheduleService;

  private Ticket mapToEntity(TicketDTO dto) {
    Ticket ticket = new Ticket();

    ticket.setId(dto.getId());
    ticket.setCreatedAt(dto.getCreatedAt());
    ticket.setPrice(dto.getPrice());
    ticket.setSeats(dto.getSeats());
    ticket.setRoom(dto.getRoom());
    ticket.setNumberSeats(dto.getNumberSeats());

    if (dto.getUser() != null) {
      ticket.setUser(userService.mapToEntity(dto.getUser()));
    }

    if (dto.getSchedule() != null) {
      ticket.setSchedule(scheduleService.mapToEntity(dto.getSchedule()));
    }

    return ticket;
  }

  private TicketDTO mapToDTOWithoutSchedule(Ticket ticket) {
    TicketDTO dto = mapToDTO(ticket);
    dto.setSchedule(null);
    return dto;
  }

    private TicketDTO mapToDTOWithoutUser(Ticket ticket) {
    TicketDTO dto = mapToDTO(ticket);
    dto.setUser(null);
    return dto;
  }

  private TicketDTO mapToDTO(Ticket ticket) {
    TicketDTO dto = new TicketDTO();

    dto.setId(ticket.getId());
    dto.setCreatedAt(ticket.getCreatedAt());
    dto.setPrice(ticket.getPrice());
    dto.setSeats(ticket.getSeats());
    dto.setRoom(ticket.getRoom());
    dto.setNumberSeats(ticket.getNumberSeats());

    if (ticket.getUser() != null) {
      dto.setUser(userService.mapToDTO(ticket.getUser()));
    }

    if (ticket.getSchedule() != null) {
      dto.setSchedule(scheduleService.mapToDTO(ticket.getSchedule()));
    }

    return dto;
  }

  public TicketDTO saveTicket(TicketDTO ticket) {
    return mapToDTO(ticketRepository.save(mapToEntity(ticket)));
  }

  public Optional<TicketDTO> getTicketById(Long id) {
    return ticketRepository.findById(id)
        .map(t -> mapToDTO(t));
  }

  public List<TicketDTO> getTicketsByUser(String id) {
    return ticketRepository.findByUserUuid(id)
        .stream()
        .map(t -> mapToDTOWithoutUser(t))
        .collect(Collectors.toList());
  }

  public List<TicketDTO> getTicketsBySchedule(Long id) {
    return ticketRepository.findByScheduleId(id)
        .stream()
        .map(t -> mapToDTOWithoutSchedule(t))
        .collect(Collectors.toList());
  }
}
