package ru.promo.consul_plan.service;

import ru.promo.consul_plan.entity.NotificationEntity;

public interface INotificationService {
    void create(NotificationEntity entity);
    NotificationEntity getById(Long id);

}
