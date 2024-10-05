package ru.promo.consul_plan.controller.domain;

import lombok.Data;
import ru.promo.consul_plan.domain.Role;

@Data
public class SignUpRequest {

    private String username;
    private String password;
    private Role role;
    private String specialization;
}
