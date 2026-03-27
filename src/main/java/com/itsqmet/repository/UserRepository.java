package com.itsqmet.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.User;
import com.itsqmet.views.UserMembership;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByEmail(String email);

  @Query(value = "select * from vista_beneficios_usuarios;", nativeQuery = true)
  Page<UserMembership> getUserMembership(Pageable pageable);

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query(value = "refresh materialized view vista_beneficios_usuarios;", nativeQuery = true)
  void refreshUsersReport();
}
