package com.reto_tecnico2.api_comercios.mapper;

import com.reto_tecnico2.api_comercios.dto.ComercioRequestDTO;
import com.reto_tecnico2.api_comercios.dto.ComercioResponseDTO;
import com.reto_tecnico2.api_comercios.models.Comercio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {SedeMapper.class})
public interface ComercioMapper {

    ComercioResponseDTO toResponseDTO(Comercio comercio);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Comercio toEntity(ComercioRequestDTO requestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateEntityFromDTO(ComercioRequestDTO requestDTO, @MappingTarget Comercio comercio);
}
