package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.StablishmentDTO;
import com.itsqmet.entity.Stablishment;
import com.itsqmet.repository.StablishmentRepository;

@Service
public class StablishmentService {
  @Autowired
  private StablishmentRepository stablishmentRepository;

  public Stablishment mapToEntity(StablishmentDTO dto) {
    Stablishment stablishment = new Stablishment();

    stablishment.setId(dto.getId());
    stablishment.setName(dto.getName());
    stablishment.setAddress(dto.getAddress());

    return stablishment;
  }

  public StablishmentDTO mapToDTO(Stablishment stablishment) {
    StablishmentDTO dto = new StablishmentDTO();

    dto.setId(stablishment.getId());
    dto.setName(stablishment.getName());
    dto.setAddress(stablishment.getAddress());

    return dto;
  }

  public List<StablishmentDTO> getAll() {
    return stablishmentRepository.findAll()
        .stream()
        .map(s -> mapToDTO(s))
        .collect(Collectors.toList());
  }

  public StablishmentDTO save(StablishmentDTO stablishment) {
    return mapToDTO(stablishmentRepository.save(mapToEntity(stablishment)));
  }

  public Optional<StablishmentDTO> getItemById(Long id) {
    return stablishmentRepository.findById(id)
        .map(s -> mapToDTO(s));
  }

  public StablishmentDTO update(Long id, StablishmentDTO stablishment) {
    Stablishment oldStablishment = stablishmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Stablishment not found"));

    oldStablishment.setName(stablishment.getName());
    oldStablishment.setAddress(stablishment.getAddress());
    
    return mapToDTO(stablishmentRepository.save(oldStablishment));
  }

  public void deleteItem(Long id) {
    stablishmentRepository.deleteById(id);
  }
}
