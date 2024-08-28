package com.example.atencionMedica.dto;

import com.example.atencionMedica.entity.Partido;
import lombok.Data;

@Data
public class LocalidadDTOEntrada {
    private String nombre;
    private PartidoDTOEntrada partidoDTOEntrada;
}
