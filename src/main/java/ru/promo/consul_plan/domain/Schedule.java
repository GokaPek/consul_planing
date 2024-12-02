package ru.promo.consul_plan.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Schedule {
    private Long id;
    private Long specialistId;
    private LocalDate date;
    private String startTime;
    private String endTime;
}