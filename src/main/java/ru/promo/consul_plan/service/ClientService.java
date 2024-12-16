package ru.promo.consul_plan.service;

import ru.promo.consul_plan.domain.Client;
import ru.promo.consul_plan.domain.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    void create(ClientEntity entity);

    Optional<ClientEntity> getEntityById(Long id);

    void update(Client dto);

    void delete(Long id);

    List<Client> getAll();
}
