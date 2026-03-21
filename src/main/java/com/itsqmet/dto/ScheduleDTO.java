package com.itsqmet.dto;

import java.time.OffsetDateTime;
import java.util.List;

import com.itsqmet.types.FunctionType;
import com.itsqmet.types.Seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
  private Long id;
  private Integer room;
  private OffsetDateTime date;
  private FunctionType type;
  private Integer availableSeats;
  private Integer occupiedSeats;
  private MovieDTO movie;
  private StablishmentDTO stablishment;
  private List<Seat> occupiedList;
}
