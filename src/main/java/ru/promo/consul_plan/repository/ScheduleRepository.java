package ru.promo.consul_plan.repository;

import ru.promo.consul_plan.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAllBySpecialistId(Long specialistId);
}
