package ru.promo.consul_plan.domain;

import lombok.Data;

@Data
public class Specialist {
    private Long id;
    private String name = "Unknown"; // Значение по умолчанию
    private String specialization;

    // Конструктор с обязательными полями
    public Specialist(Long id, String specialization) {
        this.id = id;
        this.specialization = specialization;
    }

    // Конструктор со всеми полями
    public Specialist(Long id, String name, String specialization) {
        this.id = id;
        this.name = name != null ? name : "Unknown"; // Устанавливаем значение по умолчанию, если name == null
        this.specialization = specialization;
    }
}