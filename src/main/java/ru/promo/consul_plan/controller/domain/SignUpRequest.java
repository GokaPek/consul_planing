package ru.promo.consul_plan.controller.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.promo.consul_plan.domain.entity.Role;

@Data
public class SignUpRequest {

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Email(message = "Имя пользователя должно быть корректным email-адресом")
    private String username;
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
    private String password;
    @NotNull(message = "Роль не может быть пустой")
    private Role role;
    @NotBlank(message = "Специализация не может быть пустой")
    private String specialization;
}
