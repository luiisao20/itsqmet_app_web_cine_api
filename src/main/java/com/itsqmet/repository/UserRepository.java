package com.itsqmet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.User;
import com.itsqmet.views.UserMembership;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByEmail(String email);

  @Query(value = "select * from vista_beneficios_usuarios;", nativeQuery = true)
  List<UserMembership> getUserMembership();
}
