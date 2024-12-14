package ru.promo.consul_plan.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.promo.consul_plan.domain.entity.AccountEntity;

public interface AccountService {
    AccountEntity save(AccountEntity user);

    AccountEntity create(AccountEntity user);

    AccountEntity getByUsername(String username);

    UserDetailsService userDetailsService();
}
