package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.promo.consul_plan.domain.Consultation;
import ru.promo.consul_plan.domain.entity.ConsultationEntity;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ConsultationMapper {
    @Mapping(source = "specialist.id", target = "specialistId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "schedule.id", target = "scheduleId")
    Consultation toDTO(ConsultationEntity consultationEntity);

    @Mapping(source = "specialistId", target = "specialist.id")
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "scheduleId", target = "schedule.id")
    ConsultationEntity toEntity(Consultation consultation);

    @Mapping(source = "specialist.id", target = "specialistId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "schedule.id", target = "scheduleId")
    List<Consultation> toDTOList(List<ConsultationEntity> consultationEntities);
}
