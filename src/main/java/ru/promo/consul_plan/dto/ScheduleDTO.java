package ru.promo.consul_plan.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleDTO {
    private Long id;
    private Long specialistId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}