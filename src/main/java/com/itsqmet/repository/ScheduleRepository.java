package com.itsqmet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  List<Schedule> findByStablishmentIdAndMovieId(Long stablishmentId, Long movieId);

  List<Schedule> findByMovieId(Long id);

  Optional<List<Schedule>> findByMovieTitleContainingIgnoreCase(String title);

  Optional<List<Schedule>> findByStablishmentNameContainingIgnoreCase(String name);
}
