package com.itsqmet.dto;

import java.time.OffsetDateTime;
import java.util.List;

import com.itsqmet.types.MovieStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO {
  private Long id;
  private String imageUrl;
  private Float rating;
  private OffsetDateTime releaseDate;
  private Float time;
  private String title;
  private String trailer;
  private Float revenew;
  private String overview;
  private MovieStatus status;
  private Integer totalReviews;
  private List<CategoryDTO> categories;
}
