package ru.promo.consul_plan.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.promo.consul_plan.domain.Client;
import ru.promo.consul_plan.domain.entity.ClientEntity;

import java.util.List;

public interface ClientService {

    void create(ClientEntity entity);

    ClientEntity getEntityById(Long id) throws ChangeSetPersister.NotFoundException;

    void update(Client dto);

    void delete(Long id);

    List<Client> getAll();
}
