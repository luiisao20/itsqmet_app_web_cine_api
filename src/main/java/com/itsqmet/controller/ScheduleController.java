package com.itsqmet.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
  @Autowired
  private ScheduleService scheduleService;

  @GetMapping
  public Page<ScheduleDTO> getAll(@PageableDefault(size = 10) Pageable pageable) {
    return scheduleService.getAll(pageable);
  }

  @GetMapping("/time-available")
  public List<LocalDateTime> getTimeAvaiable(@RequestParam Long movieId,
      @RequestParam Long stablishmentId,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return scheduleService.getTimeAvailable(movieId, stablishmentId, date);
  }

  @GetMapping("/movieTitle")
  public Optional<List<ScheduleDTO>> getByMovie(@RequestParam(defaultValue = "") String title) {
    return scheduleService.getItemsByMovieTitle(title);
  }

  @GetMapping("/stablishmentName")
  public Optional<Page<ScheduleDTO>> getByStablishment(@RequestParam(defaultValue = "") String name, @PageableDefault(size = 10) Pageable pageable) {
    return scheduleService.getItemsByStablishmentName(name, pageable);
  }

  @GetMapping("/{id}")
  public Optional<ScheduleDTO> getItem(@PathVariable Long id) {
    return scheduleService.getItemById(id);
  }

  @GetMapping("/stablishment/{sId}/movie/{mId}")
  public List<ScheduleDTO> getByStablishment(@PathVariable Long sId, @PathVariable Long mId) {
    return scheduleService.getItemsByStablishment(sId, mId);
  }

  @GetMapping("/movie/{id}")
  public List<ScheduleDTO> getByMovie(@PathVariable Long id) {
    return scheduleService.getItemsByMovie(id);
  }

  @PostMapping("/save")
  public ScheduleDTO save(@RequestBody ScheduleDTO entity) {
    return scheduleService.save(entity);
  }

  @PostMapping("/saveAll")
  public List<ScheduleDTO> postAll(@RequestBody List<ScheduleDTO> dtos) {
    return scheduleService.saveMultiple(dtos);
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
