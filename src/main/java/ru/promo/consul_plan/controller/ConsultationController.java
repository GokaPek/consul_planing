package ru.promo.consul_plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.service.ConsultationService;

import java.util.List;

@RestController
public class ConsultationController implements ConsultationApi {

    @Autowired
    private ConsultationService consultationService;

    public ResponseEntity<ConsultationEntity> reserveConsultation(@PathVariable(name = "clientId") Long clientId, @PathVariable(name = "scheduleId") Long scheduleId) throws ChangeSetPersister.NotFoundException {
        ConsultationEntity reservedConsultation = consultationService.reserveConsultation(scheduleId, clientId);
        return ResponseEntity.ok(reservedConsultation);
    }

    public ResponseEntity<List<ConsultationEntity>> getClientConsultations(@PathVariable(name = "clientId") Long clientId) {
        List<ConsultationEntity> clientConsultations = consultationService.getClientConsultations(clientId);
        return ResponseEntity.ok(clientConsultations);
    }

    public ResponseEntity<List<ConsultationEntity>> getSpecialistConsultations(@PathVariable(name = "specialistId") Long specialistId) {
        List<ConsultationEntity> specialistConsultations = consultationService.getSpecialistConsultations(specialistId);
        return ResponseEntity.ok(specialistConsultations);
    }

    public ResponseEntity<ConsultationEntity> confirmConsultation(@PathVariable(name = "consultationId") Long consultationId) {
        ConsultationEntity confirmedConsultation = consultationService.confirmConsultation(consultationId);
        return ResponseEntity.ok(confirmedConsultation);
    }

    public ResponseEntity<ConsultationEntity> cancelConsultation(@PathVariable(name = "consultationId") Long consultationId) {
        ConsultationEntity cancelledConsultation = consultationService.cancelConsultation(consultationId);
        return ResponseEntity.ok(cancelledConsultation);
    }
}