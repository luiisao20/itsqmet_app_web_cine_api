package com.itsqmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
  
}
