package com.itsqmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.Movie;
import com.itsqmet.types.MovieStatus;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
  List<Movie> findByTitleContainingIgnoreCase(String title);

  @Query(value = "select m.* from movies as m join movie_has_categories as mc on mc.movie_id = m.id join categories as c on c.id = mc.category_id where mc.category_id = :find_category_id", nativeQuery = true)
  List<Movie> findByCategoryId(@Param("find_category_id") Long id);

  List<Movie> findByStatus(MovieStatus status);

  @Query(value = "select m.*b from movies as m join schedules as s " +
      "on s.movie_id = m.id join stablishments as st " +
      "on st.id = s.stablishment_id where st.id = " +
      ":find_stablishment group by m.id order by m.release_date asc", nativeQuery = true)
  List<Movie> findByStablishment(@Param("find_stablishment") Long id);
}
