package ru.promo.consul_plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;
import ru.promo.consul_plan.mapper.ScheduleMapper;
import ru.promo.consul_plan.repository.ScheduleRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final SpecialistService specialistService;

    private final ScheduleMapper scheduleMapper;

    @Override
    public void create(Schedule dto) throws ChangeSetPersister.NotFoundException {
        SpecialistEntity specialist = specialistService.getEntityById(dto.getSpecialistId());

        ScheduleEntity entity = new ScheduleEntity();
        entity.setSpecialist(specialist);
        entity.setStartTime(LocalDateTime.parse(dto.getStartTime()));
        entity.setEndTime(LocalDateTime.parse(dto.getEndTime()));

        scheduleRepository.save(entity);
    }

    @Override
    public Schedule getById(Long id) {
        return scheduleMapper.toDTO(scheduleRepository.findById(id).orElse(null));
    }

    @Override
    public ScheduleEntity getEntityById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }


    @Override
    public void update(Schedule dto) throws ChangeSetPersister.NotFoundException {
        if (scheduleRepository.existsById(dto.getId())) {
            SpecialistEntity specialist = specialistService.getEntityById(dto.getSpecialistId());

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
    public List<Schedule> getAllBySpecialistId(Long specialistId) {
        return scheduleMapper.toDTOlist(scheduleRepository.findAllBySpecialistIdAndClientIsNull(specialistId));
    }

    @Override
    @Transactional
    public List<Schedule> getAll() {
        return scheduleMapper.toDTOlist(scheduleRepository.findAll());
    }

    @Override
    public List<Schedule> findAllByDateTimeBetween(LocalDate localDate) {
        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX);

        return scheduleMapper.toDTOlist(scheduleRepository.findAllByStartTimeBetween(startOfDay, endOfDay));
    }
}