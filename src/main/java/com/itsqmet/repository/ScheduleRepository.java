package com.itsqmet.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  List<Schedule> findByStablishmentIdAndMovieIdAndDateAfter(Long stablishmentId, Long movieId, OffsetDateTime date);

  List<Schedule> findByMovieId(Long id);

  Optional<List<Schedule>> findByMovieTitleContainingIgnoreCase(String title);

  Optional<Page<Schedule>> findByStablishmentNameContainingIgnoreCase(String name, Pageable pageable);

  @Query(value = "select cast(date_forbidden as timestamp) from get_time_available_for_schedule(:movie_id, :stablishment_id, cast(:date as DATE))", nativeQuery = true)
  List<LocalDateTime> getTimeAvaiableForSchedule(
      @Param("movie_id") Long movieId,
      @Param("stablishment_id") Long stablishmentId,
      @Param("date") LocalDate date);
}
