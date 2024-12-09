package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;

@Mapper(componentModel = "spring")
public interface SpecialistMapper {
    @Mapping(source = "account.username", target = "name")
    Specialist toDTO(SpecialistEntity specialistEntity);

    @Mapping(source = "name", target = "account.username")
    SpecialistEntity toEntity(Specialist specialist);
}
