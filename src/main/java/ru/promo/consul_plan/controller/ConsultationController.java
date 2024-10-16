package ru.promo.consul_plan.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.entity.ConsultationEntity;
import ru.promo.consul_plan.service.ConsultationService;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @Operation(summary = "Метод резервации клиентом расписания (создание консультации)")
    @PostMapping("/reserve/{clientId}/{scheduleId}")
    public ResponseEntity<ConsultationEntity> reserveConsultation(@PathVariable(name = "clientId") Long clientId, @PathVariable(name = "scheduleId") Long scheduleId) throws ChangeSetPersister.NotFoundException {
        ConsultationEntity reservedConsultation = consultationService.reserveConsultation(scheduleId, clientId);
        return ResponseEntity.ok(reservedConsultation);
    }

    @Operation(summary = "Консультации на которые записан клиент (включая отклонённые, которые помечены CANCELED)")
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ConsultationEntity>> getClientConsultations(@PathVariable(name = "clientId") Long clientId) {
        List<ConsultationEntity> clientConsultations = consultationService.getClientConsultations(clientId);
        return ResponseEntity.ok(clientConsultations);
    }

    @Operation(summary = "Консультации на которые назначен специалист (включая отклонённые, которые помечены CANCELED)")
    @GetMapping("/specialist/{specialistId}")
    public ResponseEntity<List<ConsultationEntity>> getSpecialistConsultations(@PathVariable(name = "specialistId") Long specialistId) {
        List<ConsultationEntity> specialistConsultations = consultationService.getSpecialistConsultations(specialistId);
        return ResponseEntity.ok(specialistConsultations);
    }

    @Operation(summary = "Метод которым специалист подтверждает консультацию")
    @PostMapping("/confirm/{consultationId}")
    public ResponseEntity<ConsultationEntity> confirmConsultation(@PathVariable(name = "consultationId") Long consultationId) {
        ConsultationEntity confirmedConsultation = consultationService.confirmConsultation(consultationId);
        return ResponseEntity.ok(confirmedConsultation);
    }

    @Operation(summary = "Метод которым специалист отклоняет консультацию")
    @PostMapping("/cancel/{consultationId}")
    public ResponseEntity<ConsultationEntity> cancelConsultation(@PathVariable(name = "consultationId") Long consultationId) {
        ConsultationEntity cancelledConsultation = consultationService.cancelConsultation(consultationId);
        return ResponseEntity.ok(cancelledConsultation);
    }
}