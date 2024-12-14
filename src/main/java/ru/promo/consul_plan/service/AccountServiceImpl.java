package ru.promo.consul_plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.entity.AccountEntity;
import ru.promo.consul_plan.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    public AccountEntity save(AccountEntity user) {
        return repository.save(user);
    }

    @Override
    public AccountEntity create(AccountEntity user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        return save(user);
    }

    @Override
    public AccountEntity getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    //todo пример как из контекста достать пользователя
//    public Account getCurrentUser() {
//        var username = SecurityContextHolder.getContext().getAuthentication().getName();
//        return getByUsername(username);
//    }
}
