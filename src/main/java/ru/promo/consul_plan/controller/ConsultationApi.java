package ru.promo.consul_plan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.promo.consul_plan.domain.Consultation;

import java.util.List;

@Tag(name = "Consultation API", description = "API для управления консультациями")
@RequestMapping("/api/consultations")
public interface ConsultationApi {

    @Operation(summary = "Резервирование консультации клиентом")
    @PostMapping("/reserve/{clientId}/{scheduleId}")
    ResponseEntity<Consultation> reserveConsultation(
            @Parameter(description = "ID клиента") @PathVariable(name = "clientId") @NotNull @Positive Long clientId,
            @Parameter(description = "ID расписания") @PathVariable(name = "scheduleId") Long scheduleId);

    @Operation(summary = "Получить все консультации клиента")
    @GetMapping("/client/{clientId}")
    ResponseEntity<List<Consultation>> getClientConsultations(
            @Parameter(description = "ID клиента") @PathVariable(name = "clientId") @NotNull @Positive Long clientId);

    @Operation(summary = "Получить все консультации специалиста")
    @GetMapping("/specialist/{specialistId}")
    ResponseEntity<List<Consultation>> getSpecialistConsultations(
            @Parameter(description = "ID специалиста") @PathVariable(name = "specialistId") @NotNull @Positive Long specialistId);

    @Operation(summary = "Подтверждение консультации специалистом")
    @PostMapping("/confirm/{consultationId}")
    ResponseEntity<Consultation> confirmConsultation(
            @Parameter(description = "ID консультации") @PathVariable(name = "consultationId") @NotNull @Positive Long consultationId);

    @Operation(summary = "Отклонение консультации специалистом")
    @PostMapping("/cancel/{consultationId}")
    ResponseEntity<Consultation> cancelConsultation(
            @Parameter(description = "ID консультации") @PathVariable(name = "consultationId") @NotNull @Positive Long consultationId);
}