package ru.promo.consul_plan.controller.domain;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;

    private String password;
}
