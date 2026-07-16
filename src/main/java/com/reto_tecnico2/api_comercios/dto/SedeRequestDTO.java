package com.reto_tecnico2.api_comercios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SedeRequestDTO {

    @NotBlank(message = "El distrito no puede estar vacío")
    private String distrito;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    private String telefono;
    private String correo;

    @NotNull(message = "El estado no puede ser nulo")
    private Boolean estado;
}
