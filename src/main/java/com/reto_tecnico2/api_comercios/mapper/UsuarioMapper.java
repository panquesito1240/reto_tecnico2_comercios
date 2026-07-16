package com.reto_tecnico2.api_comercios.mapper;

import com.reto_tecnico2.api_comercios.dto.request.UsuarioRequestDTO;
import com.reto_tecnico2.api_comercios.dto.response.UsuarioResponseDTO;
import com.reto_tecnico2.api_comercios.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponseDTO toResponseDTO(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    Usuario toEntity(UsuarioRequestDTO requestDTO);
}
