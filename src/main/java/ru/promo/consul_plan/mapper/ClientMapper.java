package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.promo.consul_plan.domain.Client;
import ru.promo.consul_plan.domain.entity.ClientEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(source = "accountEntity.username", target = "name")
    Client toDTO(ClientEntity clientEntity);

    List<Client> toDTOList(List<ClientEntity> clientList);
}
