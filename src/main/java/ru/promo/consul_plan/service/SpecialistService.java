package ru.promo.consul_plan.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;

import java.util.List;

public interface SpecialistService {
    void create(Specialist entity);

    Specialist getById(Long id) throws ChangeSetPersister.NotFoundException;

    void update(Specialist entity);

    void delete(Long id);

    List<Specialist> getAll();
}
