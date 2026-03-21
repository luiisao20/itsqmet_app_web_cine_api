package com.itsqmet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.Membership;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
  Optional<Membership> findByUserUuid(String id);

  List<Membership> findAllByOrderByCurrentVisitsDesc(Integer currentVisits);
}
