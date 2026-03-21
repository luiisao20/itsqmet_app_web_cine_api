package com.itsqmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
  List<Movie> findByTitleContainingIgnoreCase(String title);

  @Query(value = "select m.* from movies as m join movie_has_categories as mc on mc.movie_id = m.id join categories as c on c.id = mc.category_id where mc.category_id = :find_category_id", nativeQuery = true)
  List<Movie> findByCategoryId(@Param("find_category_id") Long id);
}
