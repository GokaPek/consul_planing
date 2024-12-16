package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    @Mapping(source = "specialist.id", target = "specialistId")
    @Mapping(source = "client.id", target = "clientId")
    Schedule toDTO(ScheduleEntity scheduleEntity);

    List<Schedule> toDTOlist(List<ScheduleEntity> entities);
}
