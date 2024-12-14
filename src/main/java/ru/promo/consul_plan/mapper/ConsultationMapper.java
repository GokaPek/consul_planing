package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.promo.consul_plan.domain.Consultation;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {
    @Mapping(source = "specialist.id", target = "specialistId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "schedule.id", target = "scheduleId")
    Consultation toDTO(ConsultationEntity consultationEntity);

    List<Consultation> toDTOList(List<ConsultationEntity> consultationEntities);
}
