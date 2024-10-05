package ru.promo.consul_plan.service;

import ru.promo.consul_plan.entity.ConsultationEntity;

public interface IConsultationService {
    void create(ConsultationEntity entity);
    ConsultationEntity getById(Long id);

}
