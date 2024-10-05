package ru.promo.consul_plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.consul_plan.domain.entity.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByUsername(String username);

    boolean existsByUsername(String username);

}
