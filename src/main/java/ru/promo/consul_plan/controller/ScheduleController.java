package ru.promo.consul_plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.dto.ScheduleDTO;
import ru.promo.consul_plan.dto.SpecialistDTO;
import ru.promo.consul_plan.entity.ScheduleEntity;
import ru.promo.consul_plan.entity.SpecialistEntity;
import ru.promo.consul_plan.service.ScheduleService;
import ru.promo.consul_plan.service.SpecialistService;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SpecialistService specialistService;

    @PostMapping
    public ResponseEntity<Void> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.create(scheduleDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleEntity> getScheduleById(@RequestParam(name = "id", defaultValue = "0") Long id) {
        ScheduleEntity schedule = scheduleService.getById(id);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping
    public ResponseEntity<Void> updateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.update(scheduleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@RequestParam(name = "id", defaultValue = "0") Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/specialist/{specialistId}")
    public ResponseEntity<List<ScheduleEntity>> getSchedulesBySpecialistId(@RequestParam(name = "specialist", defaultValue = "0") Long specialistId) {
        List<ScheduleEntity> schedules = scheduleService.getAllBySpecialistId(specialistId);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/specialists")
    public ResponseEntity<List<SpecialistDTO>> getAllSpecialists() {
        List<SpecialistDTO> specialists = specialistService.getAllSpecialistsDTO();
        return ResponseEntity.ok(specialists);
    }

    @GetMapping()
    public ResponseEntity<List<ScheduleEntity>> getAllSchedule() {
        List<ScheduleEntity> schedules = scheduleService.getAll();
        return ResponseEntity.ok(schedules);
    }

}