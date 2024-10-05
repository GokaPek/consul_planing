package ru.promo.consul_plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.entity.ClientEntity;
import ru.promo.consul_plan.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService{

    private final ClientRepository сlientRepository;
    @Override
    public void create(ClientEntity entity) {
        сlientRepository.save(entity);
    }

    @Override
    public ClientEntity getById(Long id) {
        return null;
    }
}
