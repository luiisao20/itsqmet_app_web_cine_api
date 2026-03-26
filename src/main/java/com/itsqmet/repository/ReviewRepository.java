package com.itsqmet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.Review;
import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
  Page<Review> findByMovieId(Long id, Pageable pageable);

  List<Review> findByUserUuid(String id);
}
