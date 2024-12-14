package ru.promo.consul_plan.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Account {
    private UUID id;
    private String username;
    private String password;
    private String role;
}
