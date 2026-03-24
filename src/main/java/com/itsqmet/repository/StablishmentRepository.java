package com.itsqmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.Stablishment;

@Repository
public interface StablishmentRepository extends JpaRepository<Stablishment, Long> {
  @Query(value = "select st.* from movies as m join " + 
  "schedules as s on s.movie_id = m.id join stablishments " +
  "as st on st.id = s.stablishment_id where m.id = :find_movie " +
  "group by st.id", nativeQuery = true)
  List<Stablishment> findByMovies(@Param("find_movie") Long id);
}
