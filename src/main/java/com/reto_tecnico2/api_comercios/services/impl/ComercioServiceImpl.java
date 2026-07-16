package com.reto_tecnico2.api_comercios.services.impl;

import com.reto_tecnico2.api_comercios.dto.ComercioRequestDTO;
import com.reto_tecnico2.api_comercios.dto.ComercioResponseDTO;
import com.reto_tecnico2.api_comercios.exception.ResourceNotFoundException;
import com.reto_tecnico2.api_comercios.models.Comercio;
import com.reto_tecnico2.api_comercios.repository.ComercioRepository;
import com.reto_tecnico2.api_comercios.services.ComercioService;
import com.reto_tecnico2.api_comercios.mapper.ComercioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComercioServiceImpl implements ComercioService {

    private final ComercioRepository comercioRepository;
    private final ComercioMapper comercioMapper;

    @Override
    public Page<ComercioResponseDTO> listarComercios(String nombre, String distrito, Boolean estado, Pageable pageable) {
        return comercioRepository.findByFiltros(nombre, distrito, estado, pageable)
                .map(comercioMapper::toResponseDTO);
    }

    @Override
    public ComercioResponseDTO obtenerComercio(Long id) {
        Comercio comercio = comercioRepository.findByIdAndEstadoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comercio no encontrado o inactivo con id: " + id));
        return comercioMapper.toResponseDTO(comercio);
    }

    @Override
    public ComercioResponseDTO crearComercio(ComercioRequestDTO requestDTO) {
        Comercio comercio = comercioMapper.toEntity(requestDTO);
        if (comercio.getEstado() == null) {
            comercio.setEstado(true);
        }
        if (comercio.getSedes() != null) {
            comercio.getSedes().forEach(sede -> sede.setComercio(comercio));
        }
        
        Comercio comercioGuardado = comercioRepository.save(comercio);
        return comercioMapper.toResponseDTO(comercioGuardado);
    }

    @Override
    public ComercioResponseDTO actualizarComercio(Long id, ComercioRequestDTO requestDTO) {
        Comercio comercio = comercioRepository.findByIdAndEstadoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comercio no encontrado o inactivo con id: " + id));

        comercioMapper.updateEntityFromDTO(requestDTO, comercio);

        if (comercio.getSedes() != null) {
            comercio.getSedes().forEach(sede -> sede.setComercio(comercio));
        }

        Comercio comercioActualizado = comercioRepository.save(comercio);
        return comercioMapper.toResponseDTO(comercioActualizado);
    }

    @Override
    public void eliminarComercio(Long id) {
        Comercio comercio = comercioRepository.findByIdAndEstadoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comercio no encontrado o inactivo con id: " + id));

        comercio.setEstado(false); // Baja lógica
        comercioRepository.save(comercio);
    }
}
