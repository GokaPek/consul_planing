package ru.promo.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.security.entity.ConsultationEntity;

public interface ConsultationRepository extends JpaRepository<ConsultationEntity, Long> {
}