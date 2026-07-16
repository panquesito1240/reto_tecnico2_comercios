package com.reto_tecnico2.api_comercios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComercioRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La razón social no puede estar vacía")
    private String razonSocial;

    private List<SedeRequestDTO> sedes;

    @NotNull(message = "El estado no puede ser nulo")
    private Boolean estado;
}
