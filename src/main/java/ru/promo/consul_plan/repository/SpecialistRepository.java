package ru.promo.consul_plan.repository;

import ru.promo.consul_plan.entity.SpecialistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialistRepository extends JpaRepository<SpecialistEntity, Long> {
}
