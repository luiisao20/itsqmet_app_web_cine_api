package com.itsqmet.dto;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
  private Long id;
  private String title;
  private String description;
  private int rating;
  private OffsetDateTime createdAt;
  private MovieDTO movie;
  private UserDTO user;
}
