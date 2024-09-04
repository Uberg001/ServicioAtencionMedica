package com.example.atencionMedica.dto;

import lombok.*;

@Data
public class PersonaVulnerableDTOEntrada {

    private String firstName;
    private String lastName;
    private String email;
    private DocumentoDTOEntrada documentoDTOEntrada;
    private DireccionDTOEntrada domicilioDTOEntrada;

}