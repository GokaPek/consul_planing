package ru.promo.consul_plan.service;

import org.springframework.data.domain.Page;
import ru.promo.consul_plan.domain.Consultation;
import ru.promo.consul_plan.domain.ConsultationEvent;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;

import java.util.List;

public interface ConsultationService {
    void create(Consultation dto);

    void update(ConsultationEntity entity);

    ConsultationEntity getById(Long id);

    Consultation reserveConsultation(Long scheduleId, Long clientId);

    List<Consultation> getClientConsultations(Long clientId);

    List<Consultation> getSpecialistConsultations(Long specialistId);

    Consultation confirmConsultation(Long consultationId);

    Consultation cancelConsultation(Long consultationId);

    Page<ConsultationEntity> getNotificationCreatedFalse(int page, int size);

    ConsultationEvent createConsultationEvent(ConsultationEntity consultation);
}
