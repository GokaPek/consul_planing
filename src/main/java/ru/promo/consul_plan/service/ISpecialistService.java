package ru.promo.consul_plan.service;

import ru.promo.consul_plan.entity.SpecialistEntity;

public interface ISpecialistService {
    void create(SpecialistEntity entity);
    SpecialistEntity getById(Long id);

}
