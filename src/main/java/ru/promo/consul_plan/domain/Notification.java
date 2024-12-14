package ru.promo.consul_plan.domain;

import lombok.Data;
import ru.promo.consul_plan.domain.entity.NotificationType;
import ru.promo.consul_plan.domain.entity.TypeStatus;

import java.time.LocalDateTime;

@Data
public class Notification {
    private Long id;
    private Long consultationId;
    private NotificationType type;
    private LocalDateTime sentDateTime;
    private TypeStatus status;
}