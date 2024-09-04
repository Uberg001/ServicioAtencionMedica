package com.example.atencionMedica.dto;

import lombok.*;

@Data

public class DireccionDTOEntrada {
    private String calle;
    private int altura;
    private LocalidadDTOEntrada localidadDTOEntrada;
    private CoordenadaDTOEntrada coordenadaDTOEntrada;
}