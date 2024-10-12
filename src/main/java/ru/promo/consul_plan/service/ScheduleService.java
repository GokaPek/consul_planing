package ru.promo.consul_plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.dto.ScheduleDTO;
import ru.promo.consul_plan.entity.ScheduleEntity;
import ru.promo.consul_plan.entity.SpecialistEntity;
import ru.promo.consul_plan.repository.ScheduleRepository;
import ru.promo.consul_plan.repository.SpecialistRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final SpecialistRepository specialistRepository;

    @Override
    public void create(ScheduleDTO dto) {
        SpecialistEntity specialist = specialistRepository.findById(dto.getSpecialistId())
                .orElseThrow(() -> new IllegalArgumentException("Specialist not found"));
        ScheduleEntity entity = new ScheduleEntity();
        entity.setSpecialist(specialist);
        entity.setDate(dto.getDate());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());

        scheduleRepository.save(entity);
    }

    @Override
    public ScheduleEntity getById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public void update(ScheduleDTO dto) {
        if (scheduleRepository.existsById(dto.getId())) {
            SpecialistEntity specialist = specialistRepository.findById(dto.getSpecialistId())
                    .orElseThrow(() -> new IllegalArgumentException("Specialist not found"));

            ScheduleEntity entity = new ScheduleEntity();
            entity.setId(dto.getId());
            entity.setSpecialist(specialist);
            entity.setDate(dto.getDate());
            entity.setStartTime(dto.getStartTime());
            entity.setEndTime(dto.getEndTime());

            scheduleRepository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<ScheduleEntity> getAllBySpecialistId(Long specialistId) {
        return scheduleRepository.findAllBySpecialistId(specialistId);
    }
}