package ru.promo.consul_plan.domain;

import lombok.Data;

@Data
public class Schedule {
    private Long id;
    private Long specialistId;
    private String startTime;
    private String endTime;
}