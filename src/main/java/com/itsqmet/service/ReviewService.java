package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.ReviewDTO;
import com.itsqmet.entity.Review;
import com.itsqmet.repository.ReviewRepository;

@Service
public class ReviewService {
  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private MovieService movieService;

  @Autowired
  private UserService userService;

  private Review mapToEntity(ReviewDTO dto) {
    Review review = new Review();

    review.setId(dto.getId());
    review.setTitle(dto.getTitle());
    review.setDescription(dto.getDescription());
    review.setRating(dto.getRating());
    review.setCreatedAt(dto.getCreatedAt());

    if (dto.getMovie() != null) {
      review.setMovie(movieService.mapToEntity(dto.getMovie()));
    }

    if (dto.getUser() != null) {
      review.setUser(userService.mapToEntity(dto.getUser()));
    }

    return review;
  }

  private ReviewDTO mapToDto(Review review) {
    ReviewDTO dto = new ReviewDTO();

    dto.setId(review.getId());
    dto.setTitle(review.getTitle());
    dto.setDescription(review.getDescription());
    dto.setRating(review.getRating());
    dto.setCreatedAt(review.getCreatedAt());

    if (review.getMovie() != null) {
      dto.setMovie(movieService.mapToDTO(review.getMovie()));
    }

    if (review.getUser() != null) {
      dto.setUser(userService.mapToDTO(review.getUser()));
    }

    return dto;
  }

  private ReviewDTO mapToDTOWithoutMovie(Review review) {
    ReviewDTO dto = mapToDto(review);
    dto.setMovie(null);
    return dto;
  }

  private ReviewDTO mapToDTOWithoutUser(Review review) {
    ReviewDTO dto = mapToDto(review);
    dto.setUser(null);
    return dto;
  }

  public ReviewDTO save(ReviewDTO review) {
    return mapToDto(reviewRepository.save(mapToEntity(review)));
  }

  public Page<ReviewDTO> getAll(Pageable pageable) {
    return reviewRepository.findAll(pageable)
        .map(r -> mapToDto(r));
  }

  public Optional<ReviewDTO> getItemById(Long id) {
    return reviewRepository.findById(id)
        .map(r -> mapToDto(r));
  }

  public void deleteItem(Long id) {
    reviewRepository.deleteById(id);
  }

  public ReviewDTO updateItem(Long id, ReviewDTO review) {
    Review oldReview = reviewRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Review not found"));

    oldReview.setTitle(review.getTitle());
    oldReview.setDescription(review.getDescription());
    oldReview.setRating(review.getRating());
    return mapToDto(reviewRepository.save(oldReview));
  }

  public Page<ReviewDTO> getReviewsByMovie(Long id, Pageable pageable) {
    return reviewRepository.findByMovieId(id, pageable)
        .map(r -> mapToDTOWithoutMovie(r));
  }

  public List<ReviewDTO> getReviewsByUser(String id) {
    return reviewRepository.findByUserUuid(id)
        .stream()
        .map(r -> mapToDTOWithoutUser(r))
        .collect(Collectors.toList());
  }
}
