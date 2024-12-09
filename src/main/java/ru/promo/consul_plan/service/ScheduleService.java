package ru.promo.consul_plan.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    void create(Schedule dto) throws ChangeSetPersister.NotFoundException;

    Schedule getById(Long id);

    ScheduleEntity getEntityById(Long id);

    void update(Schedule dto) throws ChangeSetPersister.NotFoundException;

    void update(ScheduleEntity entity);

    void delete(Long id);

    List<Schedule> getAllBySpecialistId(Long specialistId);

    List<Schedule> getAll();

    List<Schedule> findAllByDateTimeBetween(LocalDate localDate);
}
