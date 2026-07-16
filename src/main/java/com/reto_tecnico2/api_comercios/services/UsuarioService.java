package com.reto_tecnico2.api_comercios.services;

import com.reto_tecnico2.api_comercios.dto.UsuarioRequestDTO;
import com.reto_tecnico2.api_comercios.dto.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO requestDTO);
}
