package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.promo.consul_plan.domain.Client;
import ru.promo.consul_plan.domain.entity.ClientEntity;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(source = "account.username", target = "name")
    Client toDTO(ClientEntity clientEntity);

    @Mapping(source = "name", target = "account.username")
    ClientEntity toEntity(Client client);

    @Mapping(source = "account.username", target = "name")
    List<Client> toDTOList(List<ClientEntity> clientList);
}
