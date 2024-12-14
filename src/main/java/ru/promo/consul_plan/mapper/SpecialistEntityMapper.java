package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialistEntityMapper {
    @Mapping(source = "name", target = "accountEntity.username")
    SpecialistEntity toEntity(Specialist specialist);
}
