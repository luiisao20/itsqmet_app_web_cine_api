package com.itsqmet.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO {
  private Long id;
  private String title;
  private String imageUrl;
  private String time;
  private String overview;
  private Date releaseDate;
  private String rating;
  private CategoryDTO category;
  private StatusDTO status;
}
