package ru.promo.consul_plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;

public interface SpecialistRepository extends JpaRepository<SpecialistEntity, Long> {
}
