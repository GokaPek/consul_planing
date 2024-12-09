package ru.promo.consul_plan.service;

import jakarta.transaction.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;
import ru.promo.consul_plan.domain.Client;
import ru.promo.consul_plan.domain.entity.ClientEntity;

import java.util.List;

public interface ClientService {
    void create(Client dto);

    @Transactional
    void create(ClientEntity entity);

    Client getById(Long id) throws ChangeSetPersister.NotFoundException;

    ClientEntity getEntityById(Long id) throws ChangeSetPersister.NotFoundException;

    void update(Client dto);

    void delete(Long id);

    List<Client> getAll();
}
