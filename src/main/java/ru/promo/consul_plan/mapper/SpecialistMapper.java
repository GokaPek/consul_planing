package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialistMapper {
    @Mapping(source = "accountEntity.username", target = "name")
    Specialist toDTO(SpecialistEntity specialistEntity);

    List<Specialist> toDTOList(List<SpecialistEntity> entities);
}
