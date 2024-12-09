package ru.promo.consul_plan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;

import java.util.List;

@Tag(name = "Schedule API", description = "API для управления расписаниями")
@RequestMapping("/api/schedules")
public interface ScheduleApi {

    @Operation(summary = "Создать расписание")
    @PostMapping
    ResponseEntity<Void> createSchedule(@Valid @Parameter(description = "Параметры для создания расписания") @RequestBody Schedule schedule);

    @Operation(summary = "Получить расписание по ID")
    @GetMapping("/{id}")
    ResponseEntity<Schedule> getScheduleById(@Parameter(description = "ID расписания") @PathVariable(name = "id") Long id);

    @Operation(summary = "Обновить расписание")
    @PutMapping
    ResponseEntity<Void> updateSchedule(@Valid @Parameter(description = "Параметры для обновления расписания") @RequestBody Schedule schedule);

    @Operation(summary = "Удалить расписание")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSchedule(@Parameter(description = "ID расписания") @PathVariable(name = "id") Long id);

    @Operation(summary = "Получить все расписания специалиста")
    @GetMapping("/specialist/{specialistId}")
    ResponseEntity<List<Schedule>> getSchedulesBySpecialistId(@Parameter(description = "ID специалиста") @PathVariable(name = "specialistId") Long specialistId);

    @Operation(summary = "Получить всех специалистов")
    @GetMapping("/specialists")
    ResponseEntity<List<Specialist>> getAllSpecialists();

    @Operation(summary = "Получить все расписания")
    @GetMapping
    ResponseEntity<List<Schedule>> getAllSchedule();
}