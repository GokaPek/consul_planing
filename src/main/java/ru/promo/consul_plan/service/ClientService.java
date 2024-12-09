package ru.promo.consul_plan.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.promo.consul_plan.domain.Client;
import ru.promo.consul_plan.domain.entity.ClientEntity;

import java.util.List;

public interface ClientService {
    void create(Client entity);

    Client getById(Long id) throws ChangeSetPersister.NotFoundException;

    void update(Client entity);

    void delete(Long id);

    List<Client> getAll();
}
