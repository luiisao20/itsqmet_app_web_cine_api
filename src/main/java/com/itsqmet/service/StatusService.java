package com.itsqmet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.StatusDTO;
import com.itsqmet.entity.Status;
import com.itsqmet.repository.StatusRepository;

import jakarta.transaction.Transactional;

@Service
public class StatusService {
  @Autowired
  private StatusRepository statusRepository;

  private StatusDTO mapToDTO(Status status) {
    StatusDTO dto = new StatusDTO();
    dto.setId(status.getId());
    dto.setName(status.getName());
    return dto;
  }

  private Status mapToEntity(StatusDTO dto) {
    Status status = new Status();
    status.setId(dto.getId());
    status.setName(dto.getName());
    return status;
  }

  public List<StatusDTO> findAll() {
    return statusRepository.findAll()
        .stream()
        .map(s -> mapToDTO(s))
        .toList();
  }

  public Optional<StatusDTO> findById(Long id) {
    return statusRepository.findById(id)
        .map(s -> mapToDTO(s));
  }

  public StatusDTO saveStatus (StatusDTO status) {
    return mapToDTO(statusRepository.save(mapToEntity(status)));
  }

  @Transactional
  public StatusDTO getStatusWithMovie(Long id) {
    return findById(id).orElseThrow(() -> new RuntimeException("Status not found"));
  }
}
