package ru.promo.security.controller.domain;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;

    private String password;
}
