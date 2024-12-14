package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.promo.consul_plan.domain.Client;
import ru.promo.consul_plan.domain.entity.ClientEntity;

@Mapper(componentModel = "spring")
public interface ClientEntityMapper {
    @Mapping(source = "name", target = "accountEntity.username")
    ClientEntity toEntity(Client client);
}
