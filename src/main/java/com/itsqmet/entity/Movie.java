package com.itsqmet.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  @NotNull
  private String title;

  @NotNull
  private String imageUrl;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String trailer;

  @NotNull
  private String time;

  @NotNull
  @Column(columnDefinition = "TEXT")
  private String overview;

  @NotNull
  private Date releaseDate;

  @ManyToOne
  @JoinColumn(name = "status_id")
  private Status status;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToMany(mappedBy = "movie")
  private List<Ticket> tickets;

  private String rating;

  public Movie(String title, String imageUrl, String time, String overview, Date releaseDate,
      Status status, Category category, String rating) {
    this.title = title;
    this.imageUrl = imageUrl;
    this.time = time;
    this.overview = overview;
    this.releaseDate = releaseDate;
    this.status = status;
    this.category = category;
    this.rating = rating;
  }
}
