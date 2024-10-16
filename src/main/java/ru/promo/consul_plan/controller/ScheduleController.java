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
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SpecialistService specialistService;

    @Operation(summary = "Создание расписание для сотрудника (сотрудник создаёт расписание на каждый рабочий день индивидуально, расписание не повторяется")
    @PostMapping
    public ResponseEntity<Void> createSchedule( @Valid @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.create(scheduleDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleEntity> getScheduleById(@PathVariable(name = "id") Long id) {
        ScheduleEntity schedule = scheduleService.getById(id);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping
    public ResponseEntity<Void> updateSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.update(scheduleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable(name = "id") Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Вывод расписания специалиста !!Только свободные расписания, к которым не прикреплён клиент!!")
    @GetMapping("/specialist/{specialistId}")
    public ResponseEntity<List<ScheduleEntity>> getSchedulesBySpecialistId(@PathVariable(name = "specialistId") Long specialistId) {
        List<ScheduleEntity> schedules = scheduleService.getAllBySpecialistId(specialistId);
        return ResponseEntity.ok(schedules);
    }

    @Operation(summary = "Вывод всех специалистов (предназначено для страницы специалистов, чтобы клиенты могли выбрать к кому хотят записаться")
    @GetMapping("/specialists")
    public ResponseEntity<List<SpecialistDTO>> getAllSpecialists() {
        List<SpecialistDTO> specialists = specialistService.getAllSpecialistsDTO();
        return ResponseEntity.ok(specialists);
    }

    @Operation(summary = "Вывод всех расписаний, включая занятые")
    @GetMapping()
    public ResponseEntity<List<ScheduleEntity>> getAllSchedule() {
        List<ScheduleEntity> schedules = scheduleService.getAll();
        return ResponseEntity.ok(schedules);
    }

}