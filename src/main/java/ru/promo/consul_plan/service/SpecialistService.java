package ru.promo.consul_plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.entity.SpecialistEntity;
import ru.promo.consul_plan.repository.SpecialistRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialistService implements ISpecialistService{

    private final SpecialistRepository specialistRepository;
    @Override
    @Transactional
    public void create(SpecialistEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        specialistRepository.save(entity);
    }

    @Override
    @Transactional
    public SpecialistEntity getById(Long id) throws ChangeSetPersister.NotFoundException {
        return specialistRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    @Transactional
    public void update(SpecialistEntity entity) {
        if (entity == null || entity.getId() == null) {
            throw new IllegalArgumentException("Entity or ID is null");
        }
        specialistRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        specialistRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<SpecialistEntity> getAll() {
        return specialistRepository.findAll();
    }
}
