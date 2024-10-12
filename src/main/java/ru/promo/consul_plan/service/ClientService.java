package ru.promo.consul_plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.entity.ClientEntity;
import ru.promo.consul_plan.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public void create(ClientEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        clientRepository.save(entity);
    }

    @Override
    @Transactional
    public ClientEntity getById(Long id) throws ChangeSetPersister.NotFoundException {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    @Transactional
    public void update(ClientEntity entity) {
        if (entity == null || entity.getId() == null) {
            throw new IllegalArgumentException("Entity or ID is null");
        }
        clientRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<ClientEntity> getAll() {
        return clientRepository.findAll();
    }
}