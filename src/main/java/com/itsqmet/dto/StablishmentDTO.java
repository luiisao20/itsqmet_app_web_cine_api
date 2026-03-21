package com.itsqmet.dto;

import com.itsqmet.types.StablishmentAddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StablishmentDTO {
  private Long id;
  private String name;
  private StablishmentAddress address;
}
