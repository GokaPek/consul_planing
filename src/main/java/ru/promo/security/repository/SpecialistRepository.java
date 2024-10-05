package ru.promo.security.repository;

import ru.promo.security.entity.SpecialistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialistRepository extends JpaRepository<SpecialistEntity, Long> {
}
