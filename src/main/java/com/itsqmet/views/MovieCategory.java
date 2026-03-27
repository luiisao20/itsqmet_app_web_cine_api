package com.itsqmet.views;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;

public interface MovieCategory {
  @Value("#{target.titulo}")
  String getTitle();

  @Value("#{target.estado}")
  String getStatus();

  @Value("#{target.rating}")
  Float getRating();

  @Value("#{target.duracion_min}")
  Integer getDuration();

  @Value("#{target.fecha_estreno}")
  LocalDateTime getReleaseDate();

  @Value("#{target.total_reviews}")
  Integer getTotalReviews();

  @Value("#{target.categorias}")
  String getCategories();
}
