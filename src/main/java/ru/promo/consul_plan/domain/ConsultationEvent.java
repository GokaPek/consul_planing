package ru.promo.consul_plan.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ConsultationEvent {
    private Long consultationId;
    private String clientEmail;
    private String specialistEmail;
    private LocalDate consultationDate;
}

