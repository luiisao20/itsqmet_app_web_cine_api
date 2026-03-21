package com.itsqmet.entity;

import java.time.OffsetDateTime;
import java.util.List;

import com.itsqmet.types.MovieStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String imageUrl;

  private Float rating;

  @Column(nullable = false, columnDefinition = "timestamptz")
  private OffsetDateTime releaseDate;
  
  @Column(nullable = false)
  private Float time;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String trailer;

  private Float revenew;

  @NotNull
  @Column(columnDefinition = "TEXT")
  private String overview;

  @Enumerated(EnumType.STRING)
  private MovieStatus status;

  private Integer totalReviews;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "movie_has_categories",
    joinColumns = @JoinColumn(name = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private List<Category> categories;

  @OneToMany(mappedBy = "movie")
  private List<Schedule> schedules;

  @OneToMany(mappedBy = "movie")
  private List<Review> reviews;
}
