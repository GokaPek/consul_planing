package ru.promo.consul_plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.entity.NotificationEntity;
import ru.promo.consul_plan.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationEntity> createNotification(@RequestBody NotificationEntity notification) {
        notificationService.create(notification);
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationEntity> getNotificationById(@PathVariable Long id) {
        NotificationEntity notification = notificationService.getById(id);
        return ResponseEntity.ok(notification);
    }

    @PutMapping
    public ResponseEntity<NotificationEntity> updateNotification(@RequestBody NotificationEntity notification) {
        notificationService.update(notification);
        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consultation/{consultationId}")
    public ResponseEntity<List<NotificationEntity>> getNotificationsByConsultationId(@PathVariable Long consultationId) {
        List<NotificationEntity> notifications = notificationService.getAllByConsultationId(consultationId);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/reminder/{consultationId}")
    public ResponseEntity<Void> sendReminder(@PathVariable Long consultationId) {
        notificationService.sendReminder(consultationId);
        return ResponseEntity.noContent().build();
    }
}