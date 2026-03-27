package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.MovieDTO;
import com.itsqmet.entity.Movie;
import com.itsqmet.repository.MovieRepository;
import com.itsqmet.types.MovieStatus;
import com.itsqmet.views.MovieCategory;
import com.itsqmet.views.MovieRevenew;

@Service
public class MovieService {
  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private CategoryService categoryService;

  public Movie mapToEntity(MovieDTO dto) {
    Movie movie = new Movie();

    movie.setId(dto.getId());
    movie.setImageUrl(dto.getImageUrl());
    movie.setRating(dto.getRating());
    movie.setReleaseDate(dto.getReleaseDate());
    movie.setTime(dto.getTime());
    movie.setTitle(dto.getTitle());
    movie.setTrailer(dto.getTrailer());
    movie.setRevenew(dto.getRevenew());
    movie.setOverview(dto.getOverview());
    movie.setStatus(dto.getStatus());
    movie.setTotalReviews(dto.getTotalReviews());
    if (dto.getCategories() != null) {
      movie.setCategories(dto.getCategories()
          .stream()
          .map(c -> categoryService.mapToEntity(c))
          .collect(Collectors.toList()));
    }

    return movie;
  }

  public MovieDTO mapToDTO(Movie movie) {
    MovieDTO dto = new MovieDTO();

    dto.setId(movie.getId());
    dto.setImageUrl(movie.getImageUrl());
    dto.setRating(movie.getRating());
    dto.setReleaseDate(movie.getReleaseDate());
    dto.setTime(movie.getTime());
    dto.setTitle(movie.getTitle());
    dto.setTrailer(movie.getTrailer());
    dto.setRevenew(movie.getRevenew());
    dto.setOverview(movie.getOverview());
    dto.setStatus(movie.getStatus());
    dto.setTotalReviews(movie.getTotalReviews());

    if (movie.getCategories() != null) {

      dto.setCategories(movie.getCategories()
          .stream()
          .map(c -> categoryService.mapToDTO(c))
          .collect(Collectors.toList()));
    }

    return dto;
  }

  public Page<MovieDTO> showMovies(Pageable pageable) {
    return movieRepository.findAll(pageable)
        .map(this::mapToDTO);
  }

  public List<MovieDTO> saveMultipleMovies(List<MovieDTO> dtos) {
    List<Movie> movies = dtos.stream()
        .map(m -> mapToEntity(m))
        .collect(Collectors.toList());

    return movieRepository.saveAll(movies).stream()
        .map(m -> mapToDTO(m))
        .collect(Collectors.toList());
  }

  public List<MovieDTO> getMoviesByStatus(MovieStatus status) {
    return movieRepository.findByStatus(status).stream()
        .map(m -> mapToDTO(m))
        .collect(Collectors.toList());
  }

  public Page<MovieDTO> findMovieByTitle(String title, Pageable pageable) {
    if (title == null || title.isEmpty()) {
      return showMovies(pageable);
    } else {
      return movieRepository.findByTitleContainingIgnoreCase(title, pageable)
          .map(this::mapToDTO);
    }
  }

  public List<MovieDTO> findMoviesByStablishments(Long id) {
    return movieRepository.findByStablishment(id)
        .stream()
        .map(m -> mapToDTO(m))
        .collect(Collectors.toList());
  }

  public Optional<MovieDTO> findMovieById(Long id) {
    return movieRepository.findById(id).map(m -> mapToDTO(m));
  }

  public MovieDTO saveMovie(MovieDTO movie) {
    return mapToDTO(movieRepository.save(mapToEntity(movie)));
  }

  public List<MovieCategory> getMovieView() {
    return movieRepository.getMovieCategoryView();
  }

  public List<MovieRevenew> getMovieRevenewView() {
    return movieRepository.getMovieRevenewView();
  }

  public void deleteMovie(Long id) {
    movieRepository.deleteById(id);
  }

  public MovieDTO updateMovie(Long id, MovieDTO movie) {
    Movie oldMovie = movieRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Movie does not exist"));

    oldMovie.setImageUrl(movie.getImageUrl());
    oldMovie.setRating(movie.getRating());
    oldMovie.setReleaseDate(movie.getReleaseDate());
    oldMovie.setTime(movie.getTime());
    oldMovie.setTitle(movie.getTitle());
    oldMovie.setTrailer(movie.getTrailer());
    oldMovie.setRevenew(movie.getRevenew());
    oldMovie.setOverview(movie.getOverview());
    oldMovie.setStatus(movie.getStatus());
    oldMovie.setTotalReviews(movie.getTotalReviews());
    oldMovie.setCategories(movie.getCategories()
        .stream()
        .map(c -> categoryService.mapToEntity(c))
        .collect(Collectors.toList()));

    return mapToDTO(movieRepository.save(oldMovie));
  }

  public Page<MovieDTO> getByCategory(Long id, Pageable pageable) {
    return movieRepository.findByCategoryId(id, pageable)
        .map(m -> mapToDTO(m));
  }
}
