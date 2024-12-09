package ru.promo.consul_plan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.promo.consul_plan.domain.Specialist;
import ru.promo.consul_plan.domain.entity.SpecialistEntity;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface SpecialistMapper {
    @Mapping(source = "account.username", target = "name")
    Specialist toDTO(SpecialistEntity specialistEntity);

    @Mapping(source = "name", target = "account.username")
    SpecialistEntity toEntity(Specialist specialist);

    @Mapping(source = "account.username", target = "name")
    List<Specialist> toDTOList(List<SpecialistEntity> entities);
}
