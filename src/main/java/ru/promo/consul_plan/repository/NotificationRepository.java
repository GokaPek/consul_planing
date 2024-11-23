package ru.promo.consul_plan.repository;

import ru.promo.consul_plan.domain.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByConsultationId(Long consultationId);
    List<NotificationEntity> findAllByConsultationClientId(Long clientId);
}
