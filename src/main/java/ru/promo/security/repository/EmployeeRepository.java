package ru.promo.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.security.domain.entity.Account;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByUsername(String username);

    boolean existsByUsername(String username);

}
