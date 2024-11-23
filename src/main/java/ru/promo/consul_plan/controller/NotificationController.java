package ru.promo.consul_plan.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.domain.entity.NotificationEntity;
import ru.promo.consul_plan.service.ConsultationService;
import ru.promo.consul_plan.service.NotificationService;

import java.util.List;

@RestController
public class NotificationController implements NotificationApi{

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ConsultationService consultationService;


    public ResponseEntity<NotificationEntity> createNotification(@Valid @RequestBody NotificationEntity notification) {
        notificationService.create(notification);
        return ResponseEntity.ok(notification);
    }

    public ResponseEntity<NotificationEntity> getNotificationById(@PathVariable(name = "id") Long id) {
        NotificationEntity notification = notificationService.getById(id);
        return ResponseEntity.ok(notification);
    }
    public ResponseEntity<NotificationEntity> updateNotification(@Valid@RequestBody NotificationEntity notification) {
        notificationService.update(notification);
        return ResponseEntity.ok(notification);
    }

    public ResponseEntity<Void> deleteNotification(@PathVariable(name = "id") Long id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<NotificationEntity>> getNotificationsByConsultationId(@PathVariable(name = "consultationId") Long consultationId) {
        List<NotificationEntity> notificationsEntity = notificationService.getAllByConsultationId(consultationId);
        return ResponseEntity.ok(notificationsEntity);
    }

    public ResponseEntity<List<NotificationEntity>> getNotificationsByClientId(@PathVariable(name = "clientId") Long clientId) {
        List<NotificationEntity> notificationsEntity = notificationService.getAllByClientId(clientId);
        return ResponseEntity.ok(notificationsEntity);
    }

    public ResponseEntity<Void> sendReminder(@PathVariable(name = "consultationId") Long consultationId) {
        var consultation = consultationService.getById(consultationId);
        notificationService.sendReminder(consultation);
        return ResponseEntity.noContent().build();
    }
}