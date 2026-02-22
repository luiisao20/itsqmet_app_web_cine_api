package com.itsqmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
  List<Movie> findByTitleContainingIgnoreCase(String title);
}
