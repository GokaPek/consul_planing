package ru.promo.consul_plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ConsultationService scheduleService;
//    @GetMapping("/available")
//    public ResponseEntity<List<ConsultationEntity>> getAvailableConsultations() {
//        List<ConsultationEntity> availableConsultations = consultationService.getAvailableConsultations();
//        return ResponseEntity.ok(availableConsultations);
//    }

    @PostMapping("/reserve")
    public ResponseEntity<ConsultationEntity> reserveConsultation(@RequestParam(name = "schedule", defaultValue = "0") Long scheduleId, @RequestParam(name = "client", defaultValue = "0") Long clientId) throws ChangeSetPersister.NotFoundException {
        ConsultationEntity reservedConsultation = consultationService.reserveConsultation(scheduleId, clientId);
        return ResponseEntity.ok(reservedConsultation);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ConsultationEntity>> getClientConsultations(@RequestParam(name = "client", defaultValue = "0") Long clientId) {
        List<ConsultationEntity> clientConsultations = consultationService.getClientConsultations(clientId);
        return ResponseEntity.ok(clientConsultations);
    }

    @GetMapping("/specialist/{specialistId}")
    public ResponseEntity<List<ConsultationEntity>> getSpecialistConsultations(@RequestParam(name = "specialist", defaultValue = "0") Long specialistId) {
        List<ConsultationEntity> specialistConsultations = consultationService.getSpecialistConsultations(specialistId);
        return ResponseEntity.ok(specialistConsultations);
    }

    @PostMapping("/confirm/{consultationId}")
    public ResponseEntity<ConsultationEntity> confirmConsultation(@RequestParam(name = "consultation", defaultValue = "0") Long consultationId) {
        ConsultationEntity confirmedConsultation = consultationService.confirmConsultation(consultationId);
        return ResponseEntity.ok(confirmedConsultation);
    }

    @PostMapping("/cancel/{consultationId}")
    public ResponseEntity<ConsultationEntity> cancelConsultation(@RequestParam(name = "consultation", defaultValue = "0") Long consultationId) {
        ConsultationEntity cancelledConsultation = consultationService.cancelConsultation(consultationId);
        return ResponseEntity.ok(cancelledConsultation);
    }
}