package com.itsqmet.views;

import org.springframework.beans.factory.annotation.Value;

public interface MovieRevenew {
  @Value("#{target.id}")
  Long getId();

  @Value("#{target.pelicula}")
  String getTitle();

  @Value("#{target.status}")
  String getStatus();

  @Value("#{target.taquilla_total}")
  Float getRevenew();

  @Value("#{target.total_tickets}")
  Integer getTotalTickets();

  @Value("#{target.total_asientos_vendidos}")
  Integer getTotalSeats();
}
