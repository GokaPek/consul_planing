package ru.promo.consul_plan.repository;

import ru.promo.consul_plan.domain.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
