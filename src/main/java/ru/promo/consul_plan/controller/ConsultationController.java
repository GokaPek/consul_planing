package ru.promo.consul_plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.service.ConsultationServiceImpl;

import java.util.List;

@RestController
public class ConsultationController implements ConsultationApi {

    @Autowired
    private ConsultationServiceImpl consultationServiceImpl;

    public ResponseEntity<ConsultationEntity> reserveConsultation(@PathVariable(name = "clientId") Long clientId, @PathVariable(name = "scheduleId") Long scheduleId) throws ChangeSetPersister.NotFoundException {
        ConsultationEntity reservedConsultation = consultationServiceImpl.reserveConsultation(scheduleId, clientId);
        return ResponseEntity.ok(reservedConsultation);
    }

    public ResponseEntity<List<ConsultationEntity>> getClientConsultations(@PathVariable(name = "clientId") Long clientId) {
        List<ConsultationEntity> clientConsultations = consultationServiceImpl.getClientConsultations(clientId);
        return ResponseEntity.ok(clientConsultations);
    }

    public ResponseEntity<List<ConsultationEntity>> getSpecialistConsultations(@PathVariable(name = "specialistId") Long specialistId) {
        List<ConsultationEntity> specialistConsultations = consultationServiceImpl.getSpecialistConsultations(specialistId);
        return ResponseEntity.ok(specialistConsultations);
    }

    public ResponseEntity<ConsultationEntity> confirmConsultation(@PathVariable(name = "consultationId") Long consultationId) {
        ConsultationEntity confirmedConsultation = consultationServiceImpl.confirmConsultation(consultationId);
        return ResponseEntity.ok(confirmedConsultation);
    }

    public ResponseEntity<ConsultationEntity> cancelConsultation(@PathVariable(name = "consultationId") Long consultationId) {
        ConsultationEntity cancelledConsultation = consultationServiceImpl.cancelConsultation(consultationId);
        return ResponseEntity.ok(cancelledConsultation);
    }
}