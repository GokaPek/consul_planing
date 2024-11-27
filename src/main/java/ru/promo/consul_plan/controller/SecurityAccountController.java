package ru.promo.consul_plan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.consul_plan.controller.domain.SignInRequest;
import ru.promo.consul_plan.controller.domain.SignUpRequest;
import ru.promo.consul_plan.controller.domain.TokenResponse;
import ru.promo.consul_plan.service.AuthenticationService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SecurityAccountController implements SecurityAccountApi{

    private final AuthenticationService authenticationService;

    @Override
    public TokenResponse signUp(@RequestBody SignUpRequest request) {
        log.info("Sign up request: {}", request);
        return authenticationService.signUp(request);
    }

    //Авторизация пользователя
    @Override
    public TokenResponse signIn(@RequestBody SignInRequest request) {
        log.info("Sign in request: {}", request);
        return authenticationService.signIn(request);
    }
}
