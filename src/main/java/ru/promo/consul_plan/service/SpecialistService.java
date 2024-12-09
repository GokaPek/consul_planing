package ru.promo.consul_plan.service;

import jakarta.transaction.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;

import java.util.List;

public interface SpecialistService {
    void create(Specialist dto);

    void create(SpecialistEntity entity);

    Specialist getById(Long id) throws ChangeSetPersister.NotFoundException;

    SpecialistEntity getEntityById(Long id) throws ChangeSetPersister.NotFoundException;

    void update(Specialist dto);

    void delete(Long id);

    List<Specialist> getAll();
}
