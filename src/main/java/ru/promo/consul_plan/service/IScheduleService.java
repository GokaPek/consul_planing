package ru.promo.consul_plan.service;

import ru.promo.consul_plan.dto.ScheduleDTO;
import ru.promo.consul_plan.entity.ConsultationEntity;
import ru.promo.consul_plan.entity.ScheduleEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IScheduleService {
    void create(ScheduleDTO dto);
    ScheduleEntity getById(Long id);
    void update(ScheduleDTO dto);

    void update(ScheduleEntity entity);

    void delete(Long id);
    List<ScheduleEntity> getAllBySpecialistId(Long specialistId);
    List<ScheduleEntity> getAll();
    List<ScheduleEntity> findAllByDateTimeBetween(LocalDate localDate);
}
