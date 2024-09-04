package com.example.atencionMedica.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class LocalidadReportDTOSalida {
    private String localidadNombre;
    private long cantidadPersonas;
    private List<PersonaConCoordenadaDTOSalida> personasConCoordenadas;
}