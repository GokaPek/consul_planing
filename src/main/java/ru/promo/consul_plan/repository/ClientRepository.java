package ru.promo.consul_plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.consul_plan.domain.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
