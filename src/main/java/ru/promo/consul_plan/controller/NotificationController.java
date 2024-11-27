package ru.promo.consul_plan.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.domain.entity.NotificationEntity;
import ru.promo.consul_plan.service.ConsultationService;
import ru.promo.consul_plan.service.NotificationService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NotificationController implements NotificationApi{

    private final NotificationService notificationService;
    private final ConsultationService consultationService;
    @Override
    public ResponseEntity<NotificationEntity> createNotification(@Valid @RequestBody NotificationEntity notification) {
        log.info("Create notification: {}", notification);
        notificationService.create(notification);
        return ResponseEntity.ok(notification);
    }
    @Override
    public ResponseEntity<NotificationEntity> getNotificationById(@PathVariable(name = "id") Long id) {
        log.debug("Get notification by ID: {}", id);
        NotificationEntity notification = notificationService.getById(id);
        return ResponseEntity.ok(notification);
    }
    @Override
    public ResponseEntity<NotificationEntity> updateNotification(@Valid@RequestBody NotificationEntity notification) {
        log.info("Update notification: {}", notification);
        notificationService.update(notification);
        return ResponseEntity.ok(notification);
    }
    @Override
    public ResponseEntity<Void> deleteNotification(@PathVariable(name = "id") Long id) {
        log.info("Delete notification by ID: {}", id);
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Override
    public ResponseEntity<List<NotificationEntity>> getNotificationsByConsultationId(@PathVariable(name = "consultationId") Long consultationId) {
        log.debug("Get notifications by consultation ID: {}", consultationId);
        List<NotificationEntity> notificationsEntity = notificationService.getAllByConsultationId(consultationId);
        return ResponseEntity.ok(notificationsEntity);
    }
    @Override
    public ResponseEntity<List<NotificationEntity>> getNotificationsByClientId(@PathVariable(name = "clientId") Long clientId) {
        log.debug("Get notifications by client ID: {}", clientId);
        List<NotificationEntity> notificationsEntity = notificationService.getAllByClientId(clientId);
        return ResponseEntity.ok(notificationsEntity);
    }
    @Override
    public ResponseEntity<Void> sendReminder(@PathVariable(name = "consultationId") Long consultationId) {
        log.info("Send reminder for consultation ID: {}", consultationId);
        var consultation = consultationService.getById(consultationId);
        notificationService.sendReminder(consultation);
        return ResponseEntity.noContent().build();
    }
}