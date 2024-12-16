package ru.promo.consul_plan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.consul_plan.domain.Consultation;
import ru.promo.consul_plan.service.ConsultationService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConsultationController implements ConsultationApi {

    private final ConsultationService consultationService;

    @Override
    public ResponseEntity<Consultation> reserveConsultation(Long clientId, Long scheduleId) {
        log.info("Reserve consultation for client {} and schedule {}", clientId, scheduleId);
        Consultation reservedConsultation = consultationService.reserveConsultation(scheduleId, clientId);
        return ResponseEntity.ok(reservedConsultation);
    }

    @Override
    public ResponseEntity<List<Consultation>> getClientConsultations(Long clientId) {
        log.debug("Get consultations for client {}", clientId);
        List<Consultation> clientConsultations = consultationService.getClientConsultations(clientId);
        return ResponseEntity.ok(clientConsultations);
    }

    @Override
    public ResponseEntity<List<Consultation>> getSpecialistConsultations(Long specialistId) {
        log.debug("Get consultations for specialist {}", specialistId);
        List<Consultation> specialistConsultations = consultationService.getSpecialistConsultations(specialistId);
        return ResponseEntity.ok(specialistConsultations);
    }

    @Override
    public ResponseEntity<Consultation> confirmConsultation(Long consultationId) throws ChangeSetPersister.NotFoundException {
        log.info("Confirm consultation with ID {}", consultationId);
        Consultation confirmedConsultation = consultationService.confirmConsultation(consultationId);
        return ResponseEntity.ok(confirmedConsultation);
    }

    @Override
    public ResponseEntity<Consultation> cancelConsultation(Long consultationId) {
        log.info("Cancel consultation with ID {}", consultationId);
        Consultation cancelledConsultation = consultationService.cancelConsultation(consultationId);
        return ResponseEntity.ok(cancelledConsultation);
    }
}