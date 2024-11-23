package ru.promo.consul_plan.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notification {
    private Long id;
    private Long consultationId;
    private String type;
    private LocalDateTime sentDateTime;
    private String status;
}