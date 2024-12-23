package ru.promo.consul_plan.service;

import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;

import java.util.List;

public interface SpecialistService {
    void create(Specialist dto);

    void create(SpecialistEntity entity);

    SpecialistEntity getById(Long id);

    void update(Specialist dto);

    void delete(Long id);

    List<Specialist> getAll();
}
