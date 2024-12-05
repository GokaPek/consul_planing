package ru.promo.consul_plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;
import ru.promo.consul_plan.repository.ScheduleRepository;
import ru.promo.consul_plan.repository.SpecialistRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final SpecialistRepository specialistRepository;

    @Override
    public void create(Schedule dto) {
        SpecialistEntity specialist = specialistRepository.findById(dto.getSpecialistId())
                .orElseThrow(() -> new IllegalArgumentException("Specialist not found"));

        ScheduleEntity entity = new ScheduleEntity();
        entity.setSpecialist(specialist);
        entity.setStartTime(LocalDateTime.parse(dto.getStartTime()));
        entity.setEndTime(LocalDateTime.parse(dto.getEndTime()));

        scheduleRepository.save(entity);
    }

    @Override
    public ScheduleEntity getById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Schedule dto) {
        if (scheduleRepository.existsById(dto.getId())) {
            SpecialistEntity specialist = specialistRepository.findById(dto.getSpecialistId())
                    .orElseThrow(() -> new IllegalArgumentException("Specialist not found"));

            ScheduleEntity entity = new ScheduleEntity();
            entity.setId(dto.getId());
            entity.setSpecialist(specialist);
            entity.setStartTime(LocalDateTime.parse(dto.getStartTime()));
            entity.setEndTime(LocalDateTime.parse(dto.getEndTime()));

            scheduleRepository.save(entity);
        }
    }

    @Override
    public void update(ScheduleEntity entity) {
        if (scheduleRepository.existsById(entity.getId())) {
            scheduleRepository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<ScheduleEntity> getAllBySpecialistId(Long specialistId) {
        return scheduleRepository.findAllBySpecialistIdAndClientIsNull(specialistId);
    }

    @Override
    public List<ScheduleEntity> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<ScheduleEntity> findAllByDateTimeBetween(LocalDate localDate) {
        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59, 999_999_999); // Последняя наносекунда дня

        return scheduleRepository.findAllByStartTimeBetween(startOfDay, endOfDay);
    }
}