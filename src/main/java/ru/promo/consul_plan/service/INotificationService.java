package ru.promo.consul_plan.service;

import ru.promo.consul_plan.entity.NotificationEntity;

import java.util.List;

public interface INotificationService {
    void create(NotificationEntity entity);
    NotificationEntity getById(Long id);
    void update(NotificationEntity entity);
    void delete(Long id);
    List<NotificationEntity> getAllByConsultationId(Long consultationId);
    void sendReminder(Long consultationId);
}
