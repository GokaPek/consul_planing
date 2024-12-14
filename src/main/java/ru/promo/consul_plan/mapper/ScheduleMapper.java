package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.promo.consul_plan.domain.Schedule;
import ru.promo.consul_plan.domain.entity.ScheduleEntity;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    @Mapping(source = "specialist.id", target = "specialistId")
    Schedule toDTO(ScheduleEntity scheduleEntity);

    @Mapping(source = "specialistId", target = "specialist.id")
    ScheduleEntity toEntity(Schedule schedule);

    List<Schedule> toDTOlist(List<ScheduleEntity> entities);
}
