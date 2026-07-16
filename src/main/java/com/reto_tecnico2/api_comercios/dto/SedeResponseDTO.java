package com.reto_tecnico2.api_comercios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SedeResponseDTO {
    private Long id;
    private String distrito;
    private String direccion;
    private String telefono;
    private String correo;
    private Boolean estado;
}
