package ru.promo.consul_plan.dto;

import lombok.Data;

@Data
public class SpecialistDTO {
    private Long id;
    private String name = "Unknown"; // Значение по умолчанию
    private String specialization;

    // Конструктор с обязательными полями
    public SpecialistDTO(Long id, String specialization) {
        this.id = id;
        this.specialization = specialization;
    }

    // Конструктор со всеми полями
    public SpecialistDTO(Long id, String name, String specialization) {
        this.id = id;
        this.name = name != null ? name : "Unknown"; // Устанавливаем значение по умолчанию, если name == null
        this.specialization = specialization;
    }
}