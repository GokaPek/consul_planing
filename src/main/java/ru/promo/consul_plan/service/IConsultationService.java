package ru.promo.consul_plan.service;

import ru.promo.consul_plan.entity.ConsultationEntity;

import java.util.List;

public interface IConsultationService {
    void create(ConsultationEntity entity);
    ConsultationEntity getById(Long id);
    List<ConsultationEntity> getAvailableConsultations();
    ConsultationEntity reserveConsultation(ConsultationEntity consultation);
    List<ConsultationEntity> getClientConsultations(Long clientId);
    List<ConsultationEntity> getSpecialistConsultations(Long specialistId);
    ConsultationEntity confirmConsultation(Long consultationId);
    ConsultationEntity cancelConsultation(Long consultationId);
}
