package com.itsqmet.dto;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
  private Long id;
  private String name;
  private String email;
  private String message;
  private OffsetDateTime createdAt;
}
