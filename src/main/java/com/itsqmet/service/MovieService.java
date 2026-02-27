package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.CategoryDTO;
import com.itsqmet.dto.MovieDTO;
import com.itsqmet.entity.Category;
import com.itsqmet.entity.Movie;
import com.itsqmet.entity.Status;
import com.itsqmet.repository.CategoryRepository;
import com.itsqmet.repository.MovieRepository;
import com.itsqmet.repository.StatusRepository;

@Service
public class MovieService {
  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private StatusRepository statusRepository;

  private Movie maptoEntity(MovieDTO dto) {
    Movie movie = new Movie();

    movie.setId(dto.getId());
    movie.setTitle(dto.getTitle());
    movie.setImageUrl(dto.getImageUrl());
    movie.setTime(dto.getTime());
    movie.setOverview(dto.getOverview());
    movie.setReleaseDate(dto.getReleaseDate());
    movie.setRating(dto.getRating());
    movie.setTrailer(dto.getTrailer());

    if (dto.getCategory() != null) {
      Category category = categoryRepository.findById(dto.getCategory().getId())
          .orElseThrow(() -> new RuntimeException("Category does not exist"));
      movie.setCategory(category);
    }

    if (dto.getStatus() != null) {
      Status status = statusRepository.findById(dto.getStatus().getId())
          .orElseThrow(() -> new RuntimeException("Status does not exist"));
      movie.setStatus(status);
    }

    return movie;
  }

  private MovieDTO mapToDTO(Movie movie) {
    MovieDTO dto = new MovieDTO();

    dto.setId(movie.getId());
    dto.setTitle(movie.getTitle());
    dto.setImageUrl(movie.getImageUrl());
    dto.setOverview(movie.getOverview());
    dto.setReleaseDate(movie.getReleaseDate());
    dto.setRating(movie.getRating());
    dto.setTrailer(movie.getTrailer());

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

  public List<MovieDTO> saveMultipleMovies(List<MovieDTO> dtos) {
    List<Movie> movies = dtos.stream()
        .map(dto -> maptoEntity(dto))
        .collect(Collectors.toList());

    List<Movie> savedMovies = movieRepository.saveAll(movies);

    return savedMovies.stream()
        .map(movie -> mapToDTO(movie))
        .collect(Collectors.toList());
  }

  public List<MovieDTO> findMovieByTitle(String title) {
    if (title == null || title.isEmpty()) {
      return showMovies();
    } else {
      List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
      return movies.stream().map(m -> mapToDTO(m)).collect(Collectors.toList());
    }
  }

  public Optional<MovieDTO> findMovieById(Long id) {
    return movieRepository.findById(id).map(m -> mapToDTO(m));
  }

  public MovieDTO saveMovie(MovieDTO movie) {
    return mapToDTO(movieRepository.save(maptoEntity(movie)));
  }

  public void deleteMovie(Long id) {
    movieRepository.deleteById(id);
  }

  public MovieDTO updateMovie(Long id, MovieDTO movie) {
    Movie oldMovie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie does not exist"));

    oldMovie.setTitle(movie.getTitle());
    oldMovie.setImageUrl(movie.getImageUrl());
    oldMovie.setTime(movie.getTime());
    oldMovie.setOverview(movie.getOverview());
    oldMovie.setReleaseDate(movie.getReleaseDate());

    if (movie.getCategory() != null) {
      Category category = categoryRepository.findById(movie.getCategory().getId())
          .orElseThrow(() -> new RuntimeException("Category does not exist"));
      oldMovie.setCategory(category);
    }

    if (movie.getStatus() != null) {
      Status status = statusRepository.findById(movie.getStatus().getId())
          .orElseThrow(() -> new RuntimeException("Category does not exist"));
      oldMovie.setStatus(status);
    }

    Movie movieUpdated = movieRepository.save(oldMovie);

    return mapToDTO(movieUpdated);
  }
}
