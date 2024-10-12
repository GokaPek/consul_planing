package ru.promo.consul_plan.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleDTO {
    private Long id;
    private Long specialistId;
    private LocalDate date;
    private String startTime;
    private String endTime;
}