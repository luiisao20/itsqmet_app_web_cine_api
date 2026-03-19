package com.itsqmet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.entity.Movie;
import com.itsqmet.repository.MovieRepository;

@Service
public class MovieService {
  @Autowired
  private MovieRepository movieRepository;

  public List<Movie> showMovies() {
    return movieRepository.findAll();
  }

  public List<Movie> saveMultipleMovies(List<Movie> movies) {

    return movieRepository.saveAll(movies);
  }

  public List<Movie> findMovieByTitle(String title) {
    if (title == null || title.isEmpty()) {
      return showMovies();
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
    Movie oldMovie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie does not exist"));

    oldMovie.setTitle(movie.getTitle());
    oldMovie.setImageUrl(movie.getImageUrl());
    oldMovie.setTime(movie.getTime());
    oldMovie.setOverview(movie.getOverview());
    oldMovie.setReleaseDate(movie.getReleaseDate());

    return movieRepository.save(oldMovie);
  }
}
