package ru.promo.consul_plan.service;

import ru.promo.consul_plan.entity.ScheduleEntity;

public interface IScheduleService {
    void create(ScheduleEntity entity);
    ScheduleEntity getById(Long id);

}
