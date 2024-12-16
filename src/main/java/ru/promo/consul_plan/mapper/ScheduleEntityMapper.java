package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;

@Mapper(componentModel = "spring")
public interface ScheduleEntityMapper {
    @Mapping(source = "specialistId", target = "specialist.id")
    @Mapping(source = "clientId", target = "client.id")
    ScheduleEntity toEntity(Schedule schedule);
}
