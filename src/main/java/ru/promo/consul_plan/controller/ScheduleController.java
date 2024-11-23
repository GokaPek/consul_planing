package ru.promo.consul_plan.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jdk.jfr.Description;
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
public class ScheduleController implements ScheduleApi{

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SpecialistService specialistService;

    @PostMapping
    public ResponseEntity<Void> createSchedule( @Valid @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.create(scheduleDTO);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<ScheduleEntity> getScheduleById(@PathVariable(name = "id") Long id) {
        ScheduleEntity schedule = scheduleService.getById(id);
        return ResponseEntity.ok(schedule);
    }

    public ResponseEntity<Void> updateSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.update(scheduleDTO);
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

    public ResponseEntity<List<SpecialistDTO>> getAllSpecialists() {
        List<SpecialistDTO> specialists = specialistService.getAllSpecialistsDTO();
        return ResponseEntity.ok(specialists);
    }

    public ResponseEntity<List<ScheduleEntity>> getAllSchedule() {
        List<ScheduleEntity> schedules = scheduleService.getAll();
        return ResponseEntity.ok(schedules);
    }

}