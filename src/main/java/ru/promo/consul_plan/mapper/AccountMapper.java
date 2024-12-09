package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import ru.promo.consul_plan.domain.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toDTO(Account account);
    Account toEntity(Account account);
}
