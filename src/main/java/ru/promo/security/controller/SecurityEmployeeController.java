package ru.promo.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.security.controller.domain.SignInRequest;
import ru.promo.security.controller.domain.SignUpRequest;
import ru.promo.security.controller.domain.TokenResponse;
import ru.promo.security.service.AuthenticationService;


@RestController
@RequiredArgsConstructor
public class SecurityEmployeeController {

    private final AuthenticationService authenticationService;

    //Регистрация пользователя
    @PostMapping("/auth/sign-up")
    public TokenResponse signUp(@RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    //Авторизация пользователя
    @PostMapping("/auth/sign-in")
    public TokenResponse signIn(@RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
