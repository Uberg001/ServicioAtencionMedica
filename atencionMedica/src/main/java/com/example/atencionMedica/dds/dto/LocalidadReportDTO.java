package com.example.atencionMedica.dds.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LocalidadReportDTO {
    private String localidadNombre;
    private long cantidadPersonas;
    private List<String> nombresPersonas;
}