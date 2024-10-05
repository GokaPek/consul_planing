package ru.promo.consul_plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.entity.SpecialistEntity;
import ru.promo.consul_plan.repository.SpecialistRepository;

@Service
@RequiredArgsConstructor
public class SpecialistService implements ISpecialistService{

    private final SpecialistRepository specialistRepository;
    @Override
    public void create(SpecialistEntity entity) {
        specialistRepository.save(entity);
    }

    @Override
    public SpecialistEntity getById(Long id) {
        return null;
    }
}
