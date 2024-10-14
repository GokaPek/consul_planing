package ru.promo.consul_plan.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private Long id;
    private Long consultationId;
    private String type;
    private LocalDateTime sentDateTime;
    private String status;
}