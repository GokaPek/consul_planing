package ru.promo.consul_plan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.dto.ScheduleDTO;
import ru.promo.consul_plan.dto.SpecialistDTO;
import ru.promo.consul_plan.entity.ScheduleEntity;
import java.util.List;

@Tag(name = "Schedule API", description = "API для управления расписаниями")
@RequestMapping("/api/schedules")
public interface ScheduleApi {

    @Operation(summary = "Создать расписание")
    @PostMapping
    ResponseEntity<Void> createSchedule(@RequestBody ScheduleDTO scheduleDTO);

    @Operation(summary = "Получить расписание по ID")
    @GetMapping("/{id}")
    ResponseEntity<ScheduleEntity> getScheduleById(@Parameter(description = "ID расписания") @PathVariable Long id);

    @Operation(summary = "Обновить расписание")
    @PutMapping
    ResponseEntity<Void> updateSchedule(@RequestBody ScheduleDTO scheduleDTO);

    @Operation(summary = "Удалить расписание")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSchedule(@Parameter(description = "ID расписания") @PathVariable Long id);

    @Operation(summary = "Получить все расписания специалиста")
    @GetMapping("/specialist/{specialistId}")
    ResponseEntity<List<ScheduleEntity>> getSchedulesBySpecialistId(@Parameter(description = "ID специалиста") @PathVariable Long specialistId);

    @Operation(summary = "Получить всех специалистов")
    @GetMapping("/specialists")
    ResponseEntity<List<SpecialistDTO>> getAllSpecialists();

    @Operation(summary = "Получить все расписания")
    @GetMapping
    ResponseEntity<List<ScheduleEntity>> getAllSchedule();
}