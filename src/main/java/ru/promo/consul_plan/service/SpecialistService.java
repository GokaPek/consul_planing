package ru.promo.consul_plan.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;

import java.util.List;

public interface SpecialistService {
    void create(SpecialistEntity entity);
    SpecialistEntity getById(Long id) throws ChangeSetPersister.NotFoundException;
    void update(SpecialistEntity entity);
    void delete(Long id);
    List<SpecialistEntity> getAll();

    List<Specialist> getAllSpecialistsDTO();

    Specialist convertToDTO(SpecialistEntity specialist);
}
