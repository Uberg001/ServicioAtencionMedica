package com.example.atencionMedica.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

@Data
@Embeddable
@Table(name = "Partido")
public class Partido extends Persistente {

    @Column(name = "nombre")
    private String nombre;

    @Embedded
    private Provincia provincia;
}