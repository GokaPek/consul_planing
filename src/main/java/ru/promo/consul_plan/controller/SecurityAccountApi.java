package ru.promo.consul_plan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.promo.consul_plan.controller.domain.SignInRequest;
import ru.promo.consul_plan.controller.domain.SignUpRequest;
import ru.promo.consul_plan.controller.domain.TokenResponse;

@Tag(name = "Security Account API", description = "API для аутентификации и регистрации пользователей")
@RequestMapping("/auth")
public interface SecurityAccountApi {

    @Operation(summary = "Регистрация нового пользователя. Имя пользователя это ПОЧТА")
    @ApiResponse(responseCode = "200", description = "Пользователь успешно зарегистрирован")
    @PostMapping("/sign-up")
    TokenResponse signUp(
            @Parameter(description = "Данные для регистрации пользователя") @Valid @RequestBody
            SignUpRequest request);

    @Operation(summary = "Авторизация пользователя")
    @ApiResponse(responseCode = "200", description = "Пользователь успешно авторизован")
    @PostMapping("/sign-in")
    TokenResponse signIn(
            @Parameter(description = "Данные для авторизации пользователя") @Valid @RequestBody
            SignInRequest request);
}