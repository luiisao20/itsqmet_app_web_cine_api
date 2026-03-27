package com.itsqmet.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

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

  @Query(value = "select s.* " +
      "from schedules as s " +
      "join stablishments as st on st.id = s.stablishment_id " +
      "join movies as m on m.id = s.movie_id " +
      "where m.title ilike '%' || :movie_title || '%' " +
      "and st.name ilike '%' || :stab_name || '%' " +
      "and (cast(:start_date as date) is null or date(s.date) >= cast(:start_date as date))" +
      "and (cast(:end_date as date) is null or date(s.date) <= cast(:end_date as date))" +
      "order by s.date asc;", countQuery = "select count(*) " +
          "from schedules as s " +
          "join stablishments as st on st.id = s.stablishment_id " +
          "join movies as m on m.id = s.movie_id " +
          "where m.title ilike '%' || :movie_title || '%' " +
          "and st.name ilike '%' || :stab_name || '%' " +
          "and (cast(:start_date as date) is null or date(s.date) >= cast(:start_date as date)) " +
          "and (cast(:end_date as date) is null or date(s.date) <= cast(:end_date as date))", nativeQuery = true)
  Page<Schedule> findByFilters(
      @Param("stab_name") String stabName,
      @Param("movie_title") String movieTitle,
      @Param("start_date") LocalDate startDate,
      @Param("end_date") LocalDate endDate, Pageable pageable);

  @Query(value = "select cast(date_forbidden as timestamp) from get_time_available_for_schedule(:movie_id, :stablishment_id, cast(:date as DATE))", nativeQuery = true)
  List<LocalDateTime> getTimeAvaiableForSchedule(
      @Param("movie_id") Long movieId,
      @Param("stablishment_id") Long stablishmentId,
      @Param("date") LocalDate date);
}
