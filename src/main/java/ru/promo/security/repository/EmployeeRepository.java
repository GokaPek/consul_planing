package ru.promo.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.security.domain.entity.Employee;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByUsername(String username);

    boolean existsByUsername(String username);

}
