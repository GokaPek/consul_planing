package ru.promo.consul_plan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.domain.Notification;

import java.util.List;

@Tag(name = "Notification API", description = "API для управления уведомлениями")
@RequestMapping("/api/notifications")
public interface NotificationApi {

    @Operation(summary = "Создать уведомление")
    @PostMapping
    ResponseEntity<Notification> createNotification(@Parameter(description = "Параметры для создания уведомления") @Valid @RequestBody Notification notification);

    @Operation(summary = "Получить уведомление по ID")
    @GetMapping("/{id}")
    ResponseEntity<Notification> getNotificationById(@Parameter(description = "ID уведомления") @PathVariable(name = "id") Long id);

    @Operation(summary = "Обновить уведомление")
    @PutMapping
    ResponseEntity<Notification> updateNotification(@Parameter(description = "Параметры для изменения уведомления") @Valid @RequestBody Notification notification);

    @Operation(summary = "Удалить уведомление")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteNotification(@Parameter(description = "ID уведомления") @PathVariable(name = "id") Long id);

    @Operation(summary = "Получить все уведомления по ID консультации")
    @GetMapping("/consultation/{consultationId}")
    ResponseEntity<List<Notification>> getNotificationsByConsultationId(@Parameter(description = "ID консультации") @PathVariable(name = "consultationId") Long consultationId);

    @Operation(summary = "Получить все уведомления по ID клиента")
    @GetMapping("/client/{clientId}")
    ResponseEntity<List<Notification>> getNotificationsByClientId(@Parameter(description = "ID клиента") @PathVariable(name = "clientId") Long clientId);

    @Operation(summary = "Отправить напоминание по ID консультации")
    @PostMapping("/reminder/{consultationId}")
    ResponseEntity<Void> sendReminder(@Parameter(description = "ID консультации") @PathVariable(name = "consultationId") Long consultationId);
}