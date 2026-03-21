package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.ScheduleDTO;
import com.itsqmet.entity.Schedule;
import com.itsqmet.repository.MovieRepository;
import com.itsqmet.repository.ScheduleRepository;
import com.itsqmet.repository.StablishmentRepository;

@Service
public class ScheduleService {
  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private StablishmentRepository stablishmentRepository;

  @Autowired
  private MovieService movieService;

  @Autowired
  private StablishmentService stablishmentService;

  public Schedule mapToEntity(ScheduleDTO dto) {
    Schedule schedule = new Schedule();

    schedule.setId(dto.getId());
    schedule.setRoom(dto.getRoom());
    schedule.setDate(dto.getDate());
    schedule.setType(dto.getType());
    schedule.setAvailableSeats(dto.getAvailableSeats());
    schedule.setOccupiedSeats(dto.getOccupiedSeats());
    schedule.setOccupiedList(dto.getOccupiedList());

    if (dto.getMovie() != null) {
      schedule.setMovie(movieService.mapToEntity(dto.getMovie()));
    }

    if (dto.getStablishment() != null) {
      schedule.setStablishment(stablishmentService.mapToEntity(dto.getStablishment()));
    }

    return schedule;
  }

  public ScheduleDTO mapToDTO(Schedule schedule) {
    ScheduleDTO dto = new ScheduleDTO();

    dto.setId(schedule.getId());
    dto.setRoom(schedule.getRoom());
    dto.setDate(schedule.getDate());
    dto.setType(schedule.getType());
    dto.setAvailableSeats(schedule.getAvailableSeats());
    dto.setOccupiedSeats(schedule.getOccupiedSeats());
    dto.setOccupiedList(schedule.getOccupiedList());

    if (schedule.getMovie() != null) {
      dto.setMovie(movieService.mapToDTO(schedule.getMovie()));
    }

    if (schedule.getStablishment() != null) {
      dto.setStablishment(stablishmentService.mapToDTO(schedule.getStablishment()));
    }

    return dto;
  }

  private ScheduleDTO mapToDTOWithoutStablishment(Schedule schedule) {
    ScheduleDTO dto = mapToDTO(schedule);
    dto.setStablishment(null);
    return dto;
  }

  private ScheduleDTO mapToDTOWithoutMovie(Schedule schedule) {
    ScheduleDTO dto = mapToDTO(schedule);
    dto.setMovie(null);
    return dto;
  }

  public List<ScheduleDTO> getAll() {
    return scheduleRepository.findAll().stream()
        .map(s -> mapToDTO(s))
        .collect(Collectors.toList());
  }

  public ScheduleDTO save(ScheduleDTO schedule) {
    return mapToDTO(scheduleRepository.save(mapToEntity(schedule)));
  }

  public Optional<ScheduleDTO> getItemById(Long id) {
    return scheduleRepository.findById(id)
        .map(s -> mapToDTO(s));
  }

  public ScheduleDTO update(Long id, ScheduleDTO schedule) {
    Schedule oldSchedule = scheduleRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Schedule not found"));

    oldSchedule.setRoom(schedule.getRoom());
    oldSchedule.setDate(schedule.getDate());
    oldSchedule.setType(schedule.getType());
    oldSchedule.setAvailableSeats(schedule.getAvailableSeats());
    oldSchedule.setOccupiedSeats(schedule.getOccupiedSeats());

    if (schedule.getMovie() != null) {
      oldSchedule.setMovie(
          movieRepository.findById(schedule.getMovie().getId())
              .orElseThrow(() -> new RuntimeException("Movie not found")));
    }

    if (schedule.getStablishment() != null) {
      oldSchedule.setStablishment(
          stablishmentRepository.findById(schedule.getStablishment().getId())
              .orElseThrow(() -> new RuntimeException("Stablishment not found")));
    }

    return mapToDTO(scheduleRepository.save(oldSchedule));
  }

  public void delete(Long id) {
    scheduleRepository.deleteById(id);
  }

  public List<ScheduleDTO> getItemsByStablishment(Long id) {
    return scheduleRepository.findByStablishmentId(id).stream()
        .map(s -> mapToDTOWithoutStablishment(s))
        .collect(Collectors.toList());
  }

  public List<ScheduleDTO> getItemsByMovie(Long id) {
    return scheduleRepository.findByMovieId(id).stream()
        .map(s -> mapToDTOWithoutMovie(s))
        .collect(Collectors.toList());
  }
}
