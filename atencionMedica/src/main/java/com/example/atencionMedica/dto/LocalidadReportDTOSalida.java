package com.example.atencionMedica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LocalidadReportDTOSalida {
    private String localidadNombre;
    private long cantidadPersonas;
    private List<String> nombresPersonas;
}