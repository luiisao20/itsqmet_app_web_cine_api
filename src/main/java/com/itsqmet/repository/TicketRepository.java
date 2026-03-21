package com.itsqmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsqmet.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
  List<Ticket> findByUserUuid(String id);

  List<Ticket> findByScheduleId(Long id);
}
