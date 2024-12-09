package ru.promo.consul_plan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.consul_plan.domain.Notification;
import ru.promo.consul_plan.service.ConsultationService;
import ru.promo.consul_plan.service.NotificationService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NotificationController implements NotificationApi {

    private final NotificationService notificationService;
    private final ConsultationService consultationService;

    @Override
    public ResponseEntity<Notification> createNotification(Notification notification) {
        log.info("Create notification: {}", notification);
        notificationService.create(notification);
        return ResponseEntity.ok(notification);
    }

    @Override
    public ResponseEntity<Notification> getNotificationById(Long id) {
        log.debug("Get notification by ID: {}", id);
        Notification notification = notificationService.getById(id);
        return ResponseEntity.ok(notification);
    }

    @Override
    public ResponseEntity<Notification> updateNotification(Notification notification) {
        log.info("Update notification: {}", notification);
        notificationService.update(notification);
        return ResponseEntity.ok(notification);
    }

    @Override
    public ResponseEntity<Void> deleteNotification(Long id) {
        log.info("Delete notification by ID: {}", id);
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Notification>> getNotificationsByConsultationId(Long consultationId) {
        log.debug("Get notifications by consultation ID: {}", consultationId);
        List<Notification> notificationsEntity = notificationService.getAllByConsultationId(consultationId);
        return ResponseEntity.ok(notificationsEntity);
    }

    @Override
    public ResponseEntity<List<Notification>> getNotificationsByClientId(Long clientId) {
        log.debug("Get notifications by client ID: {}", clientId);
        List<Notification> notificationsEntity = notificationService.getAllByClientId(clientId);
        return ResponseEntity.ok(notificationsEntity);
    }

    @Override
    public ResponseEntity<Void> sendReminder(Long consultationId) throws ChangeSetPersister.NotFoundException {
        log.info("Send reminder for consultation ID: {}", consultationId);
        var consultation = consultationService.getEntityById(consultationId);
        notificationService.sendReminder(consultation);
        return ResponseEntity.noContent().build();
    }
}