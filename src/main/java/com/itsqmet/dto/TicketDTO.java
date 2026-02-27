package com.itsqmet.dto;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
  private Long id;
  private OffsetDateTime createdAt;
  private Double price;
  private String seats;
  private Integer room;
  private UserDTO user;
  private MovieDTO movie;
}
