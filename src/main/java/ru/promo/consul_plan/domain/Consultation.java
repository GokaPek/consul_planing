package ru.promo.consul_plan.domain;

import lombok.Data;
import ru.promo.consul_plan.domain.entity.TypeStatus;

@Data
public class Consultation {
    private Long id;
    private Long specialistId;
    private Long clientId;
    private Long scheduleId;
    private TypeStatus status;
    private Boolean notificationCreated;
}
