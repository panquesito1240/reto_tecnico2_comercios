package com.reto_tecnico2.api_comercios.mapper;

import com.reto_tecnico2.api_comercios.dto.SedeRequestDTO;
import com.reto_tecnico2.api_comercios.dto.SedeResponseDTO;
import com.reto_tecnico2.api_comercios.models.Sede;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SedeMapper {

    SedeResponseDTO toResponseDTO(Sede sede);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comercio", ignore = true)
    Sede toEntity(SedeRequestDTO requestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comercio", ignore = true)
    void updateEntityFromDTO(SedeRequestDTO requestDTO, @MappingTarget Sede sede);
}
