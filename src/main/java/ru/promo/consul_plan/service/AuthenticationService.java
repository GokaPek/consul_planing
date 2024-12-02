package ru.promo.consul_plan.service;

import ru.promo.consul_plan.controller.domain.SignInRequest;
import ru.promo.consul_plan.controller.domain.SignUpRequest;
import ru.promo.consul_plan.controller.domain.TokenResponse;

public interface AuthenticationService {
    TokenResponse signUp(SignUpRequest request);

    TokenResponse signIn(SignInRequest request);
}
