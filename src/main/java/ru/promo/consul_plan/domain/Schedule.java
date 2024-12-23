package ru.promo.consul_plan.domain;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Schedule {
    private Long id;
    @NotNull(message = "Идентификатор специалиста не может быть пустым")
    @Positive(message = "Идентификатор специалиста должен быть положительным числом")
    private Long specialistId;
    @NotNull(message = "Идентификатор клиента не может быть пустым")
    @Positive(message = "Идентификатор клиента должен быть положительным числом")
    private Long clientId;
    @NotNull(message = "Время начала не может быть пустым")
    @FutureOrPresent(message = "Время начала должно быть в будущем или настоящем")
    private LocalDateTime startTime;
    @NotNull(message = "Время окончания не может быть пустым")
    @FutureOrPresent(message = "Время окончания должно быть в будущем или настоящем")
    private LocalDateTime endTime;
}