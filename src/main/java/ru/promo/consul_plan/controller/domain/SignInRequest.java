package ru.promo.consul_plan.controller.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest {

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Email(message = "Имя пользователя должно быть корректным email-адресом")
    private String username;

    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}