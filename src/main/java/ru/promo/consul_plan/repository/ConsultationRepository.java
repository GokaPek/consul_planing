package ru.promo.consul_plan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<ConsultationEntity, Long> {

    List<ConsultationEntity> findByClientId(Long clientId);

    List<ConsultationEntity> findBySpecialistId(Long specialistId);

    Page<ConsultationEntity> findByNotificationCreatedFalse(Pageable page);
}