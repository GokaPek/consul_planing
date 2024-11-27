package ru.promo.consul_plan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.domain.entity.NotificationEntity;
import java.util.List;

@Tag(name = "Notification API", description = "API для управления уведомлениями")
@RequestMapping("/api/notifications")
public interface NotificationApi {

    @Operation(summary = "Создать уведомление")
    @PostMapping
    ResponseEntity<NotificationEntity> createNotification(@RequestBody(description = "Параметры для создания уведомления", required = true) NotificationEntity notification);

    @Operation(summary = "Получить уведомление по ID")
    @GetMapping("/{id}")
    ResponseEntity<NotificationEntity> getNotificationById(@Parameter(description = "ID уведомления") @PathVariable Long id);

    @Operation(summary = "Обновить уведомление")
    @PutMapping
    ResponseEntity<NotificationEntity> updateNotification(@RequestBody(description = "Параметры для изменения уведомления", required = true) NotificationEntity notification);

    @Operation(summary = "Удалить уведомление")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteNotification(@Parameter(description = "ID уведомления") @PathVariable Long id);

    @Operation(summary = "Получить все уведомления по ID консультации")
    @GetMapping("/consultation/{consultationId}")
    ResponseEntity<List<NotificationEntity>> getNotificationsByConsultationId(@Parameter(description = "ID консультации") @PathVariable Long consultationId);

    @Operation(summary = "Получить все уведомления по ID клиента")
    @GetMapping("/client/{clientId}")
    ResponseEntity<List<NotificationEntity>> getNotificationsByClientId(@Parameter(description = "ID клиента") @PathVariable Long clientId);

    @Operation(summary = "Отправить напоминание по ID консультации")
    @PostMapping("/reminder/{consultationId}")
    ResponseEntity<Void> sendReminder(@Parameter(description = "ID консультации") @PathVariable Long consultationId);
}