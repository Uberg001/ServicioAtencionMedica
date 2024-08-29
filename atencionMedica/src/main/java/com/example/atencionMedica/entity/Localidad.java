package com.example.atencionMedica.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

@Data
@Embeddable
public class Localidad extends Persistente {

    @Column(name = "nombre")
    private String nombre;

    @Embedded
    private Partido partido;
}