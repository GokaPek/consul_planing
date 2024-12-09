package ru.promo.consul_plan.domain;

import lombok.Data;

@Data
public class Consultation {
    private Long id;
    private Long specialistId;
    private Long clientId;
    private Long scheduleId;
    private String status;
    private boolean reminderSent;
}
