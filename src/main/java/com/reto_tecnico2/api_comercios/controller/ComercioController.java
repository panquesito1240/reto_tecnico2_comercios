package com.reto_tecnico2.api_comercios.controller;

import com.reto_tecnico2.api_comercios.dto.ApiResponse;
import com.reto_tecnico2.api_comercios.dto.ComercioRequestDTO;
import com.reto_tecnico2.api_comercios.dto.ComercioResponseDTO;
import com.reto_tecnico2.api_comercios.services.ComercioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comercios")
@RequiredArgsConstructor
public class ComercioController {

    private final ComercioService comercioService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ComercioResponseDTO>>> listarComercios(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String distrito,
            @RequestParam(required = false, defaultValue = "true") Boolean estado,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ComercioResponseDTO> comercios = comercioService.listarComercios(nombre, distrito, estado, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Comercios obtenidos exitosamente", comercios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ComercioResponseDTO>> obtenerComercio(@PathVariable Long id) {
        ComercioResponseDTO comercio = comercioService.obtenerComercio(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Comercio obtenido exitosamente", comercio));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ComercioResponseDTO>> crearComercio(@Valid @RequestBody ComercioRequestDTO requestDTO) {
        ComercioResponseDTO comercioCreado = comercioService.crearComercio(requestDTO);
        return new ResponseEntity<>(new ApiResponse<>(true, "Comercio creado exitosamente", comercioCreado), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ComercioResponseDTO>> actualizarComercio(
            @PathVariable Long id, 
            @Valid @RequestBody ComercioRequestDTO requestDTO) {
        ComercioResponseDTO comercioActualizado = comercioService.actualizarComercio(id, requestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Comercio actualizado exitosamente", comercioActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarComercio(@PathVariable Long id) {
        comercioService.eliminarComercio(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Comercio eliminado exitosamente"));
    }
}
