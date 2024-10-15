package ru.promo.consul_plan.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "consultation")
public class ConsultationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private SpecialistEntity specialist;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private ScheduleEntity schedule;

    @Enumerated(EnumType.STRING)
    private TypeStatus status;
    private boolean reminderSent;
}