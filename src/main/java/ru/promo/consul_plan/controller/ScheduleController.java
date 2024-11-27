package ru.promo.consul_plan.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;
import ru.promo.consul_plan.service.ScheduleService;
import ru.promo.consul_plan.service.SpecialistService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ScheduleController implements ScheduleApi{

    private final ScheduleService scheduleService;
    private final SpecialistService specialistService;
    @Override
    @PostMapping
    public ResponseEntity<Void> createSchedule( @Valid @RequestBody Schedule schedule) {
        log.info("Create schedule: {}", schedule);
        scheduleService.create(schedule);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<ScheduleEntity> getScheduleById(@PathVariable(name = "id") Long id) {
        log.debug("Get schedule by ID: {}", id);
        ScheduleEntity schedule = scheduleService.getById(id);
        return ResponseEntity.ok(schedule);
    }
    @Override
    public ResponseEntity<Void> updateSchedule(@Valid @RequestBody Schedule schedule) {
        log.info("Update schedule: {}", schedule);
        scheduleService.update(schedule);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<Void> deleteSchedule(@PathVariable(name = "id") Long id) {
        log.info("Delete schedule by ID: {}", id);
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Override
    public ResponseEntity<List<ScheduleEntity>> getSchedulesBySpecialistId(@PathVariable(name = "specialistId") Long specialistId) {
        log.debug("Get schedules by specialist ID: {}", specialistId);
        List<ScheduleEntity> schedules = scheduleService.getAllBySpecialistId(specialistId);
        return ResponseEntity.ok(schedules);
    }
    @Override
    public ResponseEntity<List<Specialist>> getAllSpecialists() {
        log.debug("Get all specialists");
        List<Specialist> specialists = specialistService.getAllSpecialistsDTO();
        return ResponseEntity.ok(specialists);
    }
    @Override
    public ResponseEntity<List<ScheduleEntity>> getAllSchedule() {
        log.debug("Get all schedules");
        List<ScheduleEntity> schedules = scheduleService.getAll();
        return ResponseEntity.ok(schedules);
    }

}