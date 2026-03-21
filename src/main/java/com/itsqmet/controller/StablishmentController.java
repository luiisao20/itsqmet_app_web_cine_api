package com.itsqmet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsqmet.dto.StablishmentDTO;
import com.itsqmet.service.StablishmentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/stablishments")
public class StablishmentController {
  @Autowired
  private StablishmentService stablishmentService;

  @GetMapping
  public List<StablishmentDTO> getAll() {
    return stablishmentService.getAll();
  }

  @GetMapping("/{id}")
  public Optional<StablishmentDTO> getItem(@PathVariable Long id) {
    return stablishmentService.getItemById(id);
  }

  @PostMapping("/save")
  public StablishmentDTO save(@RequestBody StablishmentDTO entity) {
    return stablishmentService.save(entity);
  }

  @PutMapping("/update/{id}")
  public StablishmentDTO update(@PathVariable Long id, @RequestBody StablishmentDTO dto) {
    return stablishmentService.update(id, dto);
  }

  @DeleteMapping("/delete/{id}")
  public void delete(@PathVariable Long id) {
    stablishmentService.deleteItem(id);
  }
}
