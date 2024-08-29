// src/main/java/com/example/atencionMedica/dds/entity/Direccion.java
package com.example.atencionMedica.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

@Data
@Entity
@Table(name = "Direccion")
public class Direccion extends Persistente {

    @Column(name = "calle")
    private String calle;

    @Column(name = "altura")
    private int altura;

    @Embedded
    private Localidad localidad;

    @Embedded
    private Coordenada coordenada;
}