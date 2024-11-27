package ru.promo.consul_plan.service;

import org.springframework.transaction.annotation.Transactional;
import ru.promo.consul_plan.controller.domain.SignInRequest;
import ru.promo.consul_plan.controller.domain.SignUpRequest;
import ru.promo.consul_plan.controller.domain.TokenResponse;

public interface AuthenticationService {
    @Transactional
    TokenResponse signUp(SignUpRequest request);

    TokenResponse signIn(SignInRequest request);
}
