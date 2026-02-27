package com.itsqmet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.MovieDTO;
import com.itsqmet.dto.TicketDTO;
import com.itsqmet.dto.UserDTO;
import com.itsqmet.entity.Movie;
import com.itsqmet.entity.Ticket;
import com.itsqmet.entity.User;
import com.itsqmet.repository.MovieRepository;
import com.itsqmet.repository.TicketRepository;
import com.itsqmet.repository.UserRepository;

@Service
public class TicketService {
  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private MovieService movieService;

  private Ticket mapToEntity(TicketDTO dto) {
    Ticket ticket = new Ticket();

    ticket.setId(dto.getId());
    ticket.setSeats(dto.getSeats());
    ticket.setPrice(dto.getPrice());
    ticket.setCreatedAt(dto.getCreatedAt());
    ticket.setRoom(dto.getRoom());

    if (dto.getUser() != null) {
      User user = userRepository.findByEmail(dto.getUser().getEmail())
          .orElseThrow(() -> new RuntimeException("User does not exist"));
      ticket.setUser(user);
    }

    if (dto.getMovie() != null) {
      Movie movie = movieRepository.findById(dto.getMovie().getId())
          .orElseThrow(() -> new RuntimeException("Movie does not exist"));
      ticket.setMovie(movie);
    }

    return ticket;
  }

  private TicketDTO mapToDTO (Ticket ticket) {
    TicketDTO dto = new TicketDTO();

    dto.setId(ticket.getId());
    dto.setSeats(ticket.getSeats());
    dto.setPrice(ticket.getPrice());
    dto.setCreatedAt(ticket.getCreatedAt());
    dto.setRoom(ticket.getRoom());

    if (ticket.getUser() != null) {
      UserDTO userDto = userService.getUserByEmail(ticket.getUser().getEmail())
          .orElseThrow(() -> new RuntimeException("User not found"));
      dto.setUser(userDto);
    }

    if (ticket.getMovie() != null) {
      MovieDTO movieDTO = movieService.findMovieById(ticket.getMovie().getId())
          .orElseThrow(() -> new RuntimeException("Movie not found"));
      dto.setMovie(movieDTO);
    }

    return dto;
  }

  public TicketDTO saveTicket(TicketDTO dto) {
    Ticket ticket = mapToEntity(dto);
    Ticket savedTicket = ticketRepository.save(ticket);
    return mapToDTO(savedTicket);
  }

  public Optional<TicketDTO> getTicketById(Long id) {
    return ticketRepository.findById(id)
        .map(t -> mapToDTO(t));
  }
}
