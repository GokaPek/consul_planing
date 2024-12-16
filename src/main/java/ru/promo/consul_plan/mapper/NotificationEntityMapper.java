package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.promo.consul_plan.domain.Notification;
import ru.promo.consul_plan.domain.entity.NotificationEntity;

@Mapper(componentModel = "spring")
public interface NotificationEntityMapper {
    @Mapping(source = "consultationId", target = "consultation.id")
    NotificationEntity toEntity(Notification notification);
}
