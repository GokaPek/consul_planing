package ru.promo.consul_plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;
import ru.promo.consul_plan.mapper.SpecialistMapper;
import ru.promo.consul_plan.repository.SpecialistRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialistServiceImpl implements SpecialistService {

    private final SpecialistRepository specialistRepository;

    private final SpecialistMapper specialistMapper;

    @Override
    @Transactional
    public void create(Specialist dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Entity is null");
        }

        var entity = specialistMapper.toEntity(dto);

        specialistRepository.save(entity);
    }

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
    public Specialist getDTOById(Long id) throws ChangeSetPersister.NotFoundException {
        return specialistMapper.toDTO(specialistRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException()));
    }

    @Override
    @Transactional
    public SpecialistEntity getById(Long id) throws ChangeSetPersister.NotFoundException {
        return specialistRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    @Transactional
    public void update(Specialist dto) {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("Entity or ID is null");
        }
        specialistRepository.save(specialistMapper.toEntity(dto));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        specialistRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Specialist> getAll() {
        return specialistMapper.toDTOList(specialistRepository.findAll());
    }
}
