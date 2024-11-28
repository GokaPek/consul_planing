package ru.promo.consul_plan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;
import ru.promo.consul_plan.service.ConsultationService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConsultationController implements ConsultationApi {

    private final ConsultationService consultationService;

    @Override
    public ResponseEntity<ConsultationEntity> reserveConsultation(Long clientId, Long scheduleId) throws ChangeSetPersister.NotFoundException {
        log.info("Reserve consultation for client {} and schedule {}", clientId, scheduleId);
        ConsultationEntity reservedConsultation = consultationService.reserveConsultation(scheduleId, clientId);
        return ResponseEntity.ok(reservedConsultation);
    }

    @Override
    public ResponseEntity<List<ConsultationEntity>> getClientConsultations(Long clientId) {
        log.debug("Get consultations for client {}", clientId);
        List<ConsultationEntity> clientConsultations = consultationService.getClientConsultations(clientId);
        return ResponseEntity.ok(clientConsultations);
    }

    @Override
    public ResponseEntity<List<ConsultationEntity>> getSpecialistConsultations(Long specialistId) {
        log.debug("Get consultations for specialist {}", specialistId);
        List<ConsultationEntity> specialistConsultations = consultationService.getSpecialistConsultations(specialistId);
        return ResponseEntity.ok(specialistConsultations);
    }

    @Override
    public ResponseEntity<ConsultationEntity> confirmConsultation(Long consultationId) {
        log.info("Confirm consultation with ID {}", consultationId);
        ConsultationEntity confirmedConsultation = consultationService.confirmConsultation(consultationId);
        return ResponseEntity.ok(confirmedConsultation);
    }

    @Override
    public ResponseEntity<ConsultationEntity> cancelConsultation(Long consultationId) {
        log.info("Cancel consultation with ID {}", consultationId);
        ConsultationEntity cancelledConsultation = consultationService.cancelConsultation(consultationId);
        return ResponseEntity.ok(cancelledConsultation);
    }
}