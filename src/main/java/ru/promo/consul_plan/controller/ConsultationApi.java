package ru.promo.consul_plan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;

import java.util.List;

@Tag(name = "Consultation API", description = "API для управления консультациями")
@RequestMapping("/api/consultations")
public interface ConsultationApi {

    @Operation(summary = "Резервирование консультации клиентом")
    @PostMapping("/reserve/{clientId}/{scheduleId}")
    ResponseEntity<ConsultationEntity> reserveConsultation(
            @Parameter(description = "ID клиента") @PathVariable Long clientId,
            @Parameter(description = "ID расписания") @PathVariable Long scheduleId) throws ChangeSetPersister.NotFoundException;

    @Operation(summary = "Получить все консультации клиента")
    @GetMapping("/client/{clientId}")
    ResponseEntity<List<ConsultationEntity>> getClientConsultations(
            @Parameter(description = "ID клиента") @PathVariable Long clientId);

    @Operation(summary = "Получить все консультации специалиста")
    @GetMapping("/specialist/{specialistId}")
    ResponseEntity<List<ConsultationEntity>> getSpecialistConsultations(
            @Parameter(description = "ID специалиста") @PathVariable Long specialistId);

    @Operation(summary = "Подтверждение консультации специалистом")
    @PostMapping("/confirm/{consultationId}")
    ResponseEntity<ConsultationEntity> confirmConsultation(
            @Parameter(description = "ID консультации") @PathVariable Long consultationId);

    @Operation(summary = "Отклонение консультации специалистом")
    @PostMapping("/cancel/{consultationId}")
    ResponseEntity<ConsultationEntity> cancelConsultation(
            @Parameter(description = "ID консультации") @PathVariable Long consultationId);
}