package com.itsqmet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsqmet.dto.MovieDTO;
import com.itsqmet.service.MovieService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:4200", methods = {
    RequestMethod.DELETE,
    RequestMethod.GET,
    RequestMethod.POST,
    RequestMethod.PUT,
    RequestMethod.OPTIONS
})
public class MovieController {
  @Autowired
  private MovieService movieService;

  @GetMapping
  public List<MovieDTO> getMovies() {
    return movieService.showMovies();
  }

  @GetMapping("/{id}")
  public Optional<MovieDTO> getMovieById(@PathVariable Long id) {
    return movieService.findMovieById(id);
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
