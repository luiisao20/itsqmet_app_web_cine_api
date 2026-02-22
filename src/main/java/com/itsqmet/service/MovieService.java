package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.CategoryDTO;
import com.itsqmet.dto.MovieDTO;
import com.itsqmet.entity.Movie;
import com.itsqmet.repository.MovieRepository;

@Service
public class MovieService {
  @Autowired
  private MovieRepository movieRepository;

  private MovieDTO mapToDTO(Movie movie) {
    MovieDTO dto = new MovieDTO();

    dto.setId(movie.getId());
    dto.setTitle(movie.getTitle());
    dto.setImageUrl(movie.getImageUrl());
    dto.setOverview(movie.getOverview());
    dto.setReleaseDate(movie.getReleaseDate());
    dto.setRating(movie.getRating());

    if (movie.getCategory() != null) {
      CategoryDTO categoryDTO = new CategoryDTO();
      categoryDTO.setId(movie.getCategory().getId());
      categoryDTO.setName(movie.getCategory().getName());
      dto.setCategory(categoryDTO);
    }
    return dto;
  }

  public List<MovieDTO> showMovies() {
    List<Movie> movies = movieRepository.findAll();

    return movies.stream().map(movie -> mapToDTO(movie)).collect(Collectors.toList());
  }

  public List<Movie> saveMultipleMovies(List<Movie> movies) {
    return movieRepository.saveAll(movies);
  }

  public List<Movie> findMovieByTitle(String title) {
    if (title == null || title.isEmpty()) {
      return movieRepository.findAll();
    } else {
      return movieRepository.findByTitleContainingIgnoreCase(title);
    }
  }

  public Optional<Movie> findMovieById(Long id) {
    return movieRepository.findById(id);
  }

  public Movie saveMovie(Movie movie) {
    return movieRepository.save(movie);
  }

  public void deleteMovie(Long id) {
    movieRepository.deleteById(id);
  }

  public Movie updateMovie(Long id, Movie movie) {
    Movie oldMovie = findMovieById(id).orElseThrow(() -> new RuntimeException("Movie does not exist"));

    oldMovie.setTitle(movie.getTitle());
    oldMovie.setImageUrl(movie.getImageUrl());
    oldMovie.setTime(movie.getTime());
    oldMovie.setOverview(movie.getOverview());
    oldMovie.setReleaseDate(movie.getReleaseDate());
    oldMovie.setStatus(movie.getStatus());
    oldMovie.setCategory(movie.getCategory());

    return movieRepository.save(oldMovie);
  }
}
