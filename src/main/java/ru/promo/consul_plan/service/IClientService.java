package ru.promo.consul_plan.service;

import ru.promo.consul_plan.entity.ClientEntity;

public interface IClientService {
    void create(ClientEntity entity);
    ClientEntity getById(Long id);

}
