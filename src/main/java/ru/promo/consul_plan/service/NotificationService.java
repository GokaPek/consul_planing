package ru.promo.consul_plan.service;

import ru.promo.consul_plan.domain.Consultation;
import ru.promo.consul_plan.domain.Notification;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.domain.entity.NotificationEntity;

import java.util.List;

public interface NotificationService {
    void create(Notification entity);

    Notification getById(Long id);

    void update(Notification entity);

    void delete(Long id);

    List<Notification> getAllByConsultationId(Long consultationId);

    List<Notification> getAllByClientId(Long clientId);

    void sendReminder(Consultation consultation);
}
