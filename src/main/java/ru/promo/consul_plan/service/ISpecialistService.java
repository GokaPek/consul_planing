package ru.promo.consul_plan.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.promo.consul_plan.entity.SpecialistEntity;

import java.util.List;

public interface ISpecialistService {
    void create(SpecialistEntity entity);
    SpecialistEntity getById(Long id) throws ChangeSetPersister.NotFoundException;
    void update(SpecialistEntity entity);
    void delete(Long id);
    List<SpecialistEntity> getAll();
}
