package ru.promo.consul_plan.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.promo.consul_plan.domain.entity.Account;

public interface AccountService {
    Account save(Account user);

    Account create(Account user);

    Account getByUsername(String username);

    UserDetailsService userDetailsService();
}
