package com.reto_tecnico2.api_comercios.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComercioResponseDTO {
    private Long id;
    private String nombre;
    private String razonSocial;
    private Boolean estado;
    private List<SedeResponseDTO> sedes;
}
