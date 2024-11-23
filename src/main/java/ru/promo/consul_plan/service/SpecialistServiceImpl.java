package ru.promo.consul_plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;
import ru.promo.consul_plan.repository.SpecialistRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialistServiceImpl implements SpecialistService {

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

    @Override
    public List<Specialist> getAllSpecialistsDTO() {
        List<SpecialistEntity> specialists = specialistRepository.findAll();
        return specialists.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Specialist convertToDTO(SpecialistEntity specialist) {
         return new Specialist(
                specialist.getId(),
                specialist.getAccount().getUsername(),
                specialist.getSpecialization()
        );
    }
}
