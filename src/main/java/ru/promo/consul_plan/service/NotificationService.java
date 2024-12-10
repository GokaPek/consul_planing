package ru.promo.consul_plan.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.promo.consul_plan.domain.Notification;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.domain.entity.NotificationEntity;

import java.util.List;

public interface NotificationService {
    void create(Notification dto);

    void create(NotificationEntity entity);

    Notification getDTOById(Long id);

    void update(Notification dto);

    void delete(Long id);

    List<Notification> getAllByConsultationId(Long consultationId);

    List<Notification> getAllByClientId(Long clientId);

    void sendReminder(ConsultationEntity consultationEntity) throws ChangeSetPersister.NotFoundException;
}
