package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.promo.consul_plan.domain.Account;
import ru.promo.consul_plan.domain.entity.AccountEntity;

@Component
@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toDTO(Account account);

    AccountEntity toEntity(Account account);
}
