package com.itsqmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.Stablishment;

@Repository
public interface StablishmentRepository extends JpaRepository<Stablishment, Long> {
  
}
