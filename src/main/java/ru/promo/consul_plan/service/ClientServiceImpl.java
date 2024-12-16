package ru.promo.consul_plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.promo.consul_plan.domain.Client;
import ru.promo.consul_plan.domain.entity.ClientEntity;
import ru.promo.consul_plan.mapper.ClientEntityMapper;
import ru.promo.consul_plan.mapper.ClientMapper;
import ru.promo.consul_plan.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;
    private final ClientEntityMapper clientEntityMapper;

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
    public ClientEntity getEntityById(Long id) throws ChangeSetPersister.NotFoundException {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    @Transactional
    public void update(Client dto) {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("Entity or ID is null");
        }
        clientRepository.save(clientEntityMapper.toEntity(dto));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Client> getAll() {
        return clientMapper.toDTOList(clientRepository.findAll());
    }
}