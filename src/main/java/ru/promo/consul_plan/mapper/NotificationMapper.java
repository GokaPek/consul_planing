package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.promo.consul_plan.domain.Notification;
import ru.promo.consul_plan.domain.entity.NotificationEntity;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface NotificationMapper {
    @Mapping(source = "consultation.id", target = "consultationId")
    Notification toDTO(NotificationEntity notificationEntity);

    @Mapping(source = "consultationId", target = "consultation.id")
    NotificationEntity toEntity(Notification notification);

    List<Notification> toDTOList(List<NotificationEntity> entities);
}
