package ru.promo.consul_plan.service;

import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    void create(Schedule dto);

    Schedule getById(Long id);

    void update(Schedule dto);

    void delete(Long id);

    List<Schedule> getAllBySpecialistId(Long specialistId);

    List<Schedule> getAll();

    List<Schedule> findAllByDateTimeBetween(LocalDate localDate);
}
