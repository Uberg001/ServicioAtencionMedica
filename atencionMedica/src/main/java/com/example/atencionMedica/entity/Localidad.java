package com.example.atencionMedica.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

@Data
@Embeddable
public class Localidad {

    @Column(name = "nombre_localidad")
    private String nombre;

    @Embedded
    private Partido partido;
}