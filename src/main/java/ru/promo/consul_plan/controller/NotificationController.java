package ru.promo.consul_plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.dto.NotificationDTO;
import ru.promo.consul_plan.entity.NotificationEntity;
import ru.promo.consul_plan.service.ConsultationService;
import ru.promo.consul_plan.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ConsultationService consultationService;


    @PostMapping
    public ResponseEntity<NotificationEntity> createNotification(@RequestBody NotificationEntity notification) {
        notificationService.create(notification);
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationEntity> getNotificationById(@RequestParam(name = "id", defaultValue = "0") Long id) {
        NotificationEntity notification = notificationService.getById(id);
        return ResponseEntity.ok(notification);
    }

    @PutMapping
    public ResponseEntity<NotificationEntity> updateNotification(@RequestBody NotificationEntity notification) {
        notificationService.update(notification);
        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@RequestParam(name = "id", defaultValue = "0") Long id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consultation/{consultationId}")
    public ResponseEntity<List<NotificationEntity>> getNotificationsByConsultationId(@RequestParam(name = "consultation", defaultValue = "0") Long consultationId) {
        List<NotificationEntity> notificationsEntity = notificationService.getAllByConsultationId(consultationId);
        return ResponseEntity.ok(notificationsEntity);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<NotificationEntity>> getNotificationsByClientId(@RequestParam(name = "client", defaultValue = "0") Long clientId) {
        List<NotificationEntity> notificationsEntity = notificationService.getAllByClientId(clientId);
        return ResponseEntity.ok(notificationsEntity);
    }

    @PostMapping("/reminder/{consultationId}")
    public ResponseEntity<Void> sendReminder(@RequestParam(name = "consultation", defaultValue = "0") Long consultationId) {
        var consultation = consultationService.getById(consultationId);
        notificationService.sendReminder(consultation);
        return ResponseEntity.noContent().build();
    }
}