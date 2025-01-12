package ru.promo.consul_plan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsultationDetails {
    private Long consultationId;
    private String clientEmail;
    private String specialistEmail;
}

