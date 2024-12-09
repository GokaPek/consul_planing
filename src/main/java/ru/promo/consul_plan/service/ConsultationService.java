package ru.promo.consul_plan.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.promo.consul_plan.domain.Consultation;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;

import java.util.List;

public interface ConsultationService {
    void create(Consultation entity);

    Consultation getById(Long id);

    Consultation reserveConsultation(Long scheduleId, Long clientId) throws ChangeSetPersister.NotFoundException;

    List<Consultation> getClientConsultations(Long clientId);

    List<Consultation> getSpecialistConsultations(Long specialistId);

    Consultation confirmConsultation(Long consultationId);

    Consultation cancelConsultation(Long consultationId);
}
