package ru.promo.consul_plan.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;
import ru.promo.consul_plan.service.ScheduleServiceImpl;
import ru.promo.consul_plan.service.SpecialistServiceImpl;

import java.util.List;

@RestController
public class ScheduleController implements ScheduleApi{

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    @Autowired
    private SpecialistServiceImpl specialistServiceImpl;

    @PostMapping
    public ResponseEntity<Void> createSchedule( @Valid @RequestBody Schedule schedule) {
        scheduleServiceImpl.create(schedule);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<ScheduleEntity> getScheduleById(@PathVariable(name = "id") Long id) {
        ScheduleEntity schedule = scheduleServiceImpl.getById(id);
        return ResponseEntity.ok(schedule);
    }

    public ResponseEntity<Void> updateSchedule(@Valid @RequestBody Schedule schedule) {
        scheduleServiceImpl.update(schedule);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteSchedule(@PathVariable(name = "id") Long id) {
        scheduleServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<ScheduleEntity>> getSchedulesBySpecialistId(@PathVariable(name = "specialistId") Long specialistId) {
        List<ScheduleEntity> schedules = scheduleServiceImpl.getAllBySpecialistId(specialistId);
        return ResponseEntity.ok(schedules);
    }

    public ResponseEntity<List<Specialist>> getAllSpecialists() {
        List<Specialist> specialists = specialistServiceImpl.getAllSpecialistsDTO();
        return ResponseEntity.ok(specialists);
    }

    public ResponseEntity<List<ScheduleEntity>> getAllSchedule() {
        List<ScheduleEntity> schedules = scheduleServiceImpl.getAll();
        return ResponseEntity.ok(schedules);
    }

}