package com.reto_tecnico2.api_comercios.services.impl;

import com.reto_tecnico2.api_comercios.dto.UsuarioRequestDTO;
import com.reto_tecnico2.api_comercios.dto.UsuarioResponseDTO;
import com.reto_tecnico2.api_comercios.mapper.UsuarioMapper;
import com.reto_tecnico2.api_comercios.models.Usuario;
import com.reto_tecnico2.api_comercios.repository.UsuarioRepository;
import com.reto_tecnico2.api_comercios.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO requestDTO) {
        if (usuarioRepository.existsByUsername(requestDTO.getUsername())) {
            throw new IllegalArgumentException("El username ya está en uso");
        }

        Usuario usuario = usuarioMapper.toEntity(requestDTO);
        usuario.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(usuarioGuardado);
    }
}
