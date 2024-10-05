package ru.promo.consul_plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.consul_plan.entity.ConsultationEntity;

public interface ConsultationRepository extends JpaRepository<ConsultationEntity, Long> {
}