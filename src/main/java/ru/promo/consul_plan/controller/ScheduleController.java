package ru.promo.consul_plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.dto.ScheduleDTO;
import ru.promo.consul_plan.entity.ScheduleEntity;
import ru.promo.consul_plan.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Void> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.create(scheduleDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleEntity> getScheduleById(@PathVariable Long id) {
        ScheduleEntity schedule = scheduleService.getById(id);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping
    public ResponseEntity<Void> updateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.update(scheduleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/specialist/{specialistId}")
    public ResponseEntity<List<ScheduleEntity>> getSchedulesBySpecialistId(@PathVariable Long specialistId) {
        List<ScheduleEntity> schedules = scheduleService.getAllBySpecialistId(specialistId);
        return ResponseEntity.ok(schedules);
    }
}