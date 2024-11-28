package ru.promo.consul_plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.consul_plan.domain.entity.NotificationEntity;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByConsultationId(Long consultationId);

    List<NotificationEntity> findAllByConsultationClientId(Long clientId);
}
