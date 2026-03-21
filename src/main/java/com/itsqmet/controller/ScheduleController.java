package com.itsqmet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsqmet.dto.ScheduleDTO;
import com.itsqmet.service.ScheduleService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
  @Autowired
  private ScheduleService scheduleService;

  @GetMapping
  public List<ScheduleDTO> getAll() {
    return scheduleService.getAll();
  }

  @GetMapping("/{id}")
  public Optional<ScheduleDTO> getItem(@PathVariable Long id) {
    return scheduleService.getItemById(id);
  }

  @GetMapping("/stablishment/{id}")
  public List<ScheduleDTO> getByStablishment(@PathVariable Long id) {
    return scheduleService.getItemsByStablishment(id);
  }

  @GetMapping("/movie/{id}")
  public List<ScheduleDTO> getByMovie(@PathVariable Long id) {
    return scheduleService.getItemsByMovie(id);
  }

  @PostMapping("/save")
  public ScheduleDTO save(@RequestBody ScheduleDTO entity) {
    return scheduleService.save(entity);
  }

  @PutMapping("/update/{id}")
  public ScheduleDTO update(@PathVariable Long id, @RequestBody ScheduleDTO entity) {
    return scheduleService.update(id, entity);
  }

  @DeleteMapping("/delete/{id}")
  public void delete(@PathVariable Long id) {
    scheduleService.delete(id);
  }
}
