package ru.promo.consul_plan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.entity.ScheduleEntity;
import ru.promo.consul_plan.repository.ScheduleRepository;

import java.util.List;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void create(ScheduleEntity entity) {
        scheduleRepository.save(entity);
    }

    @Override
    public ScheduleEntity getById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
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
        return scheduleRepository.findAllBySpecialistId(specialistId);
    }
}