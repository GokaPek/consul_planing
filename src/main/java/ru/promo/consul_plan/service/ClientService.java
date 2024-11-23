package ru.promo.consul_plan.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.promo.consul_plan.domain.entity.ClientEntity;

import java.util.List;

public interface ClientService {
    void create(ClientEntity entity);
    ClientEntity getById(Long id) throws ChangeSetPersister.NotFoundException;
    void update(ClientEntity entity);
    void delete(Long id);
    List<ClientEntity> getAll();
}
