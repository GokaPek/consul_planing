package ru.promo.consul_plan.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

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

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
