package ru.promo.consul_plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.entity.ScheduleEntity;
import ru.promo.consul_plan.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleEntity> createSchedule(@RequestBody ScheduleEntity schedule) {
        scheduleService.create(schedule);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleEntity> getScheduleById(@PathVariable Long id) {
        ScheduleEntity schedule = scheduleService.getById(id);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping
    public ResponseEntity<ScheduleEntity> updateSchedule(@RequestBody ScheduleEntity schedule) {
        scheduleService.update(schedule);
        return ResponseEntity.ok(schedule);
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