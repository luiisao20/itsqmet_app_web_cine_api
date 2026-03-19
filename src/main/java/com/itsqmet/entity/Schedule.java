package com.itsqmet.entity;

import java.time.OffsetDateTime;
import java.util.List;

import com.itsqmet.types.FunctionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Integer room;

  @Column(nullable = false, columnDefinition = "timestamptz")
  private OffsetDateTime date;

  @Enumerated(EnumType.STRING)
  private FunctionType type;

  private Integer availableSeats;

  private Integer occupiedSeats;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  private Movie movie;

  @ManyToOne
  @JoinColumn(name = "stablishment_id")
  private Stablishment stablishment;

  @OneToMany(mappedBy = "schedule")
  private List<Ticket> tickets;
}
