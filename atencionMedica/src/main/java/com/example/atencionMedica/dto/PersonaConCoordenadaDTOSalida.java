package com.example.atencionMedica.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class PersonaConCoordenadaDTOSalida {
    private String nombre;
    private CoordenadaDTOEntrada coordenada;
}
