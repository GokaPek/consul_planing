package ru.promo.consul_plan.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Schedule {
    private Long id;
    private Long specialistId;
    private Long clientId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}