package ru.promo.consul_plan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.service.ScheduleService;
import ru.promo.consul_plan.service.SpecialistService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ScheduleController implements ScheduleApi {

    private final ScheduleService scheduleService;
    private final SpecialistService specialistService;

    @Override
    public ResponseEntity<Void> createSchedule(Schedule schedule) {
        log.info("Create schedule: {}", schedule);
        scheduleService.create(schedule);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Schedule> getScheduleById(Long id) {
        log.debug("Get schedule by ID: {}", id);
        Schedule schedule = scheduleService.getDTOById(id);
        return ResponseEntity.ok(schedule);
    }

    @Override
    public ResponseEntity<Void> updateSchedule(Schedule schedule) {
        log.info("Update schedule: {}", schedule);
        scheduleService.update(schedule);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteSchedule(Long id) {
        log.info("Delete schedule by ID: {}", id);
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Schedule>> getSchedulesBySpecialistId(Long specialistId) {
        log.debug("Get schedules by specialist ID: {}", specialistId);
        List<Schedule> schedules = scheduleService.getAllBySpecialistId(specialistId);
        return ResponseEntity.ok(schedules);
    }

    @Override
    public ResponseEntity<List<Specialist>> getAllSpecialists() {
        log.debug("Get all specialists");
        List<Specialist> specialists = specialistService.getAll();
        return ResponseEntity.ok(specialists);
    }

    @Override
    public ResponseEntity<List<Schedule>> getSchedulesByDate(@RequestParam LocalDate date) {
        log.info("Get schedules by date: {}", date);
        List<Schedule> schedules = scheduleService.findAllByDateTimeBetween(date);
        return ResponseEntity.ok(schedules);
    }

}