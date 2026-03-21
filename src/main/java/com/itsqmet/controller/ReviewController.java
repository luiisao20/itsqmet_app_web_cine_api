package com.itsqmet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsqmet.dto.ReviewDTO;
import com.itsqmet.service.ReviewService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
  @Autowired
  private ReviewService reviewService;

  @GetMapping
  public List<ReviewDTO> getAll() {
    return reviewService.getAll();
  }

  @GetMapping("/movie/{id}")
  public List<ReviewDTO> getByMovie(@PathVariable Long id) {
    return reviewService.getReviewsByMovie(id);
  }

  @GetMapping("/user/{id}")
  public List<ReviewDTO> getByUser(@PathVariable String id) {
    return reviewService.getReviewsByUser(id);
  }

  @PostMapping("/save")
  public ReviewDTO save(@RequestBody ReviewDTO review) {
    return reviewService.save(review);
  }

  @PutMapping("/update/{id}")
  public ReviewDTO update(@PathVariable Long id, @RequestBody ReviewDTO dto) {
    return reviewService.updateItem(id, dto);
  }

  @DeleteMapping("/delete/{id}")
  public void delete(@PathVariable Long id) {
    reviewService.deleteItem(id);
  }

}
