package com.itsqmet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsqmet.dto.MovieDTO;
import com.itsqmet.service.MovieService;
import com.itsqmet.types.MovieStatus;
import com.itsqmet.views.MovieCategory;
import com.itsqmet.views.MovieRevenew;

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

  @GetMapping
  public Page<MovieDTO> getMovies(
      @RequestParam(defaultValue = "") String title,
      @PageableDefault(size = 10) Pageable pageable) {
    return movieService.findMovieByTitle(title, pageable);
  }

  @GetMapping("/status")
  public List<MovieDTO> getByStatus(@RequestParam(required = false) String status) {
    MovieStatus movieStatus = MovieStatus.valueOf(status);
    return movieService.getMoviesByStatus(movieStatus);
  }

  @GetMapping("/category-view")
  public Page<MovieCategory> getView(@PageableDefault(size = 10) Pageable pageable) {
    return movieService.getMovieView(pageable);
  }

  @GetMapping("/revenew-view")
  public Page<MovieRevenew> getRevenewView(@PageableDefault(size = 10) Pageable pageable) {
    return movieService.getMovieRevenewView(pageable);
  }

  @GetMapping("/refresh-financial")
  public void refreshView() {
    movieService.reloadView();
  }

  @GetMapping("/{id}")
  public Optional<MovieDTO> getMovieById(@PathVariable Long id) {
    return movieService.findMovieById(id);
  }

  @GetMapping("/stablishment/{id}")
  public List<MovieDTO> getByStablishment(@PathVariable Long id) {
    return movieService.findMoviesByStablishments(id);
  }

  @GetMapping("/category/{id}")
  public Page<MovieDTO> getItemsByCategory(
      @PathVariable Long id,
      @PageableDefault(size = 10) Pageable pageable) {
    return movieService.getByCategory(id, pageable);
  }

  @PostMapping("/saveAll")
  public List<MovieDTO> saveAllMovies(@RequestBody List<MovieDTO> movies) {
    return movieService.saveMultipleMovies(movies);
  }

  @PostMapping("/save")
  public MovieDTO saveMovie(@RequestBody MovieDTO movie) {
    return movieService.saveMovie(movie);
  }

  @PutMapping("/update/{id}")
  public MovieDTO updtaeMovie(@PathVariable Long id, @RequestBody MovieDTO movie) {
    return movieService.updateMovie(id, movie);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteMovie(@PathVariable Long id) {
    movieService.deleteMovie(id);
  }

}
