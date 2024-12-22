package ru.promo.consul_plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;
import ru.promo.consul_plan.exception.CustomIllegalArgumentException;
import ru.promo.consul_plan.exception.NotFoundException;
import ru.promo.consul_plan.mapper.SpecialistEntityMapper;
import ru.promo.consul_plan.mapper.SpecialistMapper;
import ru.promo.consul_plan.repository.SpecialistRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialistServiceImpl implements SpecialistService {

    private final SpecialistRepository specialistRepository;

    private final SpecialistMapper specialistMapper;
    private final SpecialistEntityMapper specialistEntityMapper;

    @Override
    @Transactional
    public void create(Specialist dto) {
        if (dto == null) {
            throw new CustomIllegalArgumentException("Недостаточно данных для создания специалиста");
        }

        var entity = specialistEntityMapper.toEntity(dto);

        specialistRepository.save(entity);
    }

    @Override
    @Transactional
    public void create(SpecialistEntity entity) {
        if (entity == null) {
            throw new CustomIllegalArgumentException("Недостаточно данных для создания специалиста");
        }
        specialistRepository.save(entity);
    }

    @Override
    @Transactional
    public SpecialistEntity getById(Long id) {
        return specialistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Specialist not found with id: " + id));
    }

    @Override
    @Transactional
    public void update(Specialist dto) {
        if (dto == null || dto.getId() == null) {
            throw new CustomIllegalArgumentException("Недостаточно данных для обновления специалиста");
        }
        specialistRepository.save(specialistEntityMapper.toEntity(dto));
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
