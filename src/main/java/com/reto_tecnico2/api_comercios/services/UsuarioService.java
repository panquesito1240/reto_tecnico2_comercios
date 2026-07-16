package com.reto_tecnico2.api_comercios.services;

import com.reto_tecnico2.api_comercios.dto.request.UsuarioRequestDTO;
import com.reto_tecnico2.api_comercios.dto.response.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO requestDTO);
}
