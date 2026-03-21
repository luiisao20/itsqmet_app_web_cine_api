package com.itsqmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.Review;
import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findByMovieId(Long id);

  List<Review> findByUserUuid(String id);
}
