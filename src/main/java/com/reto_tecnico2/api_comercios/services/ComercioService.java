package com.reto_tecnico2.api_comercios.services;

import com.reto_tecnico2.api_comercios.dto.ComercioRequestDTO;
import com.reto_tecnico2.api_comercios.dto.ComercioResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComercioService {
    Page<ComercioResponseDTO> listarComercios(String nombre, String distrito, Boolean estado, Pageable pageable);
    ComercioResponseDTO obtenerComercio(Long id);
    ComercioResponseDTO crearComercio(ComercioRequestDTO requestDTO);
    ComercioResponseDTO actualizarComercio(Long id, ComercioRequestDTO requestDTO);
    void eliminarComercio(Long id); // baja lógica
}
