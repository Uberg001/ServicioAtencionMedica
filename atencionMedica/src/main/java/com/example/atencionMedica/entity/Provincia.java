package com.example.atencionMedica.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Embeddable
public class Provincia {

    @Column(name = "nombre_provincia")
    private String nombre;
}