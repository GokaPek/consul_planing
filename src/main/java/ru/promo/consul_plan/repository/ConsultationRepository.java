package ru.promo.consul_plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.promo.consul_plan.entity.ConsultationEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<ConsultationEntity, Long> {
    @Query("SELECT c FROM ConsultationEntity c WHERE c.status = :status")
    List<ConsultationEntity> findByStatus(@Param("status") String status);

    @Query("SELECT c FROM ConsultationEntity c WHERE c.client.id = :clientId")
    List<ConsultationEntity> findByClientId(@Param("clientId") Long clientId);

    @Query("SELECT c FROM ConsultationEntity c WHERE c.specialist.id = :specialistId")
    List<ConsultationEntity> findBySpecialistId(@Param("specialistId") Long specialistId);

    List<ConsultationEntity> findAllByDateTimeBetween(LocalDateTime start, LocalDateTime end);
}