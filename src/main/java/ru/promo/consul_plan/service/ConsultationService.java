package ru.promo.consul_plan.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;

import java.util.List;

public interface ConsultationService {
    void create(ConsultationEntity entity);

    ConsultationEntity getById(Long id);

    ConsultationEntity reserveConsultation(Long scheduleId, Long clientId) throws ChangeSetPersister.NotFoundException;

    List<ConsultationEntity> getClientConsultations(Long clientId);

    List<ConsultationEntity> getSpecialistConsultations(Long specialistId);

    ConsultationEntity confirmConsultation(Long consultationId);

    ConsultationEntity cancelConsultation(Long consultationId);
}
