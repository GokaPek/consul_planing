package ru.promo.consul_plan.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;
import ru.promo.consul_plan.service.ScheduleService;
import ru.promo.consul_plan.service.SpecialistService;

import java.util.List;

@RestController
public class ScheduleController implements ScheduleApi{

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SpecialistService specialistService;

    @PostMapping
    public ResponseEntity<Void> createSchedule( @Valid @RequestBody Schedule schedule) {
        scheduleService.create(schedule);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<ScheduleEntity> getScheduleById(@PathVariable(name = "id") Long id) {
        ScheduleEntity schedule = scheduleService.getById(id);
        return ResponseEntity.ok(schedule);
    }

    public ResponseEntity<Void> updateSchedule(@Valid @RequestBody Schedule schedule) {
        scheduleService.update(schedule);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteSchedule(@PathVariable(name = "id") Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<ScheduleEntity>> getSchedulesBySpecialistId(@PathVariable(name = "specialistId") Long specialistId) {
        List<ScheduleEntity> schedules = scheduleService.getAllBySpecialistId(specialistId);
        return ResponseEntity.ok(schedules);
    }

    public ResponseEntity<List<Specialist>> getAllSpecialists() {
        List<Specialist> specialists = specialistService.getAllSpecialistsDTO();
        return ResponseEntity.ok(specialists);
    }

    public ResponseEntity<List<ScheduleEntity>> getAllSchedule() {
        List<ScheduleEntity> schedules = scheduleService.getAll();
        return ResponseEntity.ok(schedules);
    }

}