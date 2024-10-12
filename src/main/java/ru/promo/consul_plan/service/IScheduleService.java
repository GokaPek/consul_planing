package ru.promo.consul_plan.service;

import ru.promo.consul_plan.entity.ScheduleEntity;

import java.util.List;

public interface IScheduleService {
    void create(ScheduleEntity entity);
    ScheduleEntity getById(Long id);
    void update(ScheduleEntity entity);
    void delete(Long id);
    List<ScheduleEntity> getAllBySpecialistId(Long specialistId);
}
