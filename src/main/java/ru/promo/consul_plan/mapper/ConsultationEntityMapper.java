package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.promo.consul_plan.domain.Consultation;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;

@Mapper(componentModel = "spring")
public interface ConsultationEntityMapper {
    @Mapping(source = "specialistId", target = "specialist.id")
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "scheduleId", target = "schedule.id")
    ConsultationEntity toEntity(Consultation consultation);
}
