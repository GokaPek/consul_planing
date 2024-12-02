package ru.promo.consul_plan.service;

import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    void create(Schedule dto);

    ScheduleEntity getById(Long id);

    void update(Schedule dto);

    void update(ScheduleEntity entity);

    void delete(Long id);

    List<ScheduleEntity> getAllBySpecialistId(Long specialistId);

    List<ScheduleEntity> getAll();

    List<ScheduleEntity> findAllByDateTimeBetween(LocalDate localDate);
}
