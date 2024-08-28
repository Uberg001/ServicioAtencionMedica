package com.example.atencionMedica.dto;

import com.example.atencionMedica.entity.Coordenada;
import lombok.Data;

@Data

public class DireccionDTOEntrada {
    private String calle;
    private int altura;
    private LocalidadDTOEntrada localidadDTOEntrada;
    private CoordenadaDTOEntrada coordenadaDTOEntrada;
}
