package com.itsqmet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsqmet.dto.CategoryDTO;
import com.itsqmet.entity.Movie;
import com.itsqmet.service.CategoryService;
import com.itsqmet.service.MovieService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/movies")
public class MovieController {
  @Autowired
  private MovieService movieService;

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/categories")
  public List<CategoryDTO> getCategory() {
    return categoryService.findAll();
  }

  @GetMapping
  public List<Movie> getMovies() {
    return movieService.showMovies();
  }

  @GetMapping("/{id}")
  public Optional<Movie> getMovieById(@PathVariable Long id) {
    return movieService.findMovieById(id);
  }

  @PostMapping("/saveAll")
  public List<Movie> saveAllMovies(@RequestBody List<Movie> movies) {
    return movieService.saveMultipleMovies(movies);
  }

  @PostMapping("/save")
  public Movie saveMovie(@RequestBody Movie movie) {
    return movieService.saveMovie(movie);
  }

  @PutMapping("/update/{id}")
  public Movie updtaeMovie(@PathVariable Long id, @RequestBody Movie movie) {
    return movieService.updateMovie(id, movie);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteMovie(@PathVariable Long id) {
    movieService.deleteMovie(id);
  }

}
