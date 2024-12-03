package ru.promo.consul_plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAllBySpecialistId(Long specialistId);

    List<ScheduleEntity> findAllBySpecialistIdAndClientIsNull(Long specialistId);

    List<ScheduleEntity> findAllByStartTime(LocalDate date);
}
