package ru.promo.consul_plan.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private ConsultationEntity consultation;

    @Enumerated(EnumType.STRING)
    private TypeStatus type;
    private LocalDateTime sentDateTime;
    private String status;
}
