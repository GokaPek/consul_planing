package ru.promo.consul_plan.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private SpecialistEntity specialist;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
