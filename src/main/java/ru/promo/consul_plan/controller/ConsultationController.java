package ru.promo.consul_plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/available")
    public ResponseEntity<List<ConsultationEntity>> getAvailableConsultations() {
        List<ConsultationEntity> availableConsultations = consultationService.getAvailableConsultations();
        return ResponseEntity.ok(availableConsultations);
    }

    @PostMapping("/reserve")
    public ResponseEntity<ConsultationEntity> reserveConsultation(@RequestBody ConsultationEntity consultation) {
        ConsultationEntity reservedConsultation = consultationService.reserveConsultation(consultation);
        return ResponseEntity.ok(reservedConsultation);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ConsultationEntity>> getClientConsultations(@PathVariable Long clientId) {
        List<ConsultationEntity> clientConsultations = consultationService.getClientConsultations(clientId);
        return ResponseEntity.ok(clientConsultations);
    }

    @GetMapping("/specialist/{specialistId}")
    public ResponseEntity<List<ConsultationEntity>> getSpecialistConsultations(@PathVariable Long specialistId) {
        List<ConsultationEntity> specialistConsultations = consultationService.getSpecialistConsultations(specialistId);
        return ResponseEntity.ok(specialistConsultations);
    }

    @PostMapping("/confirm/{consultationId}")
    public ResponseEntity<ConsultationEntity> confirmConsultation(@PathVariable Long consultationId) {
        ConsultationEntity confirmedConsultation = consultationService.confirmConsultation(consultationId);
        return ResponseEntity.ok(confirmedConsultation);
    }

    @PostMapping("/cancel/{consultationId}")
    public ResponseEntity<ConsultationEntity> cancelConsultation(@PathVariable Long consultationId) {
        ConsultationEntity cancelledConsultation = consultationService.cancelConsultation(consultationId);
        return ResponseEntity.ok(cancelledConsultation);
    }
}