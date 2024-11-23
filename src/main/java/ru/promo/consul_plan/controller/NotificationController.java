package ru.promo.consul_plan.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.domain.entity.NotificationEntity;
import ru.promo.consul_plan.service.ConsultationServiceImpl;
import ru.promo.consul_plan.service.NotificationServiceImpl;

import java.util.List;

@RestController
public class NotificationController implements NotificationApi{

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;
    @Autowired
    private ConsultationServiceImpl consultationServiceImpl;


    public ResponseEntity<NotificationEntity> createNotification(@Valid @RequestBody NotificationEntity notification) {
        notificationServiceImpl.create(notification);
        return ResponseEntity.ok(notification);
    }

    public ResponseEntity<NotificationEntity> getNotificationById(@PathVariable(name = "id") Long id) {
        NotificationEntity notification = notificationServiceImpl.getById(id);
        return ResponseEntity.ok(notification);
    }
    public ResponseEntity<NotificationEntity> updateNotification(@Valid@RequestBody NotificationEntity notification) {
        notificationServiceImpl.update(notification);
        return ResponseEntity.ok(notification);
    }

    public ResponseEntity<Void> deleteNotification(@PathVariable(name = "id") Long id) {
        notificationServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<NotificationEntity>> getNotificationsByConsultationId(@PathVariable(name = "consultationId") Long consultationId) {
        List<NotificationEntity> notificationsEntity = notificationServiceImpl.getAllByConsultationId(consultationId);
        return ResponseEntity.ok(notificationsEntity);
    }

    public ResponseEntity<List<NotificationEntity>> getNotificationsByClientId(@PathVariable(name = "clientId") Long clientId) {
        List<NotificationEntity> notificationsEntity = notificationServiceImpl.getAllByClientId(clientId);
        return ResponseEntity.ok(notificationsEntity);
    }

    public ResponseEntity<Void> sendReminder(@PathVariable(name = "consultationId") Long consultationId) {
        var consultation = consultationServiceImpl.getById(consultationId);
        notificationServiceImpl.sendReminder(consultation);
        return ResponseEntity.noContent().build();
    }
}