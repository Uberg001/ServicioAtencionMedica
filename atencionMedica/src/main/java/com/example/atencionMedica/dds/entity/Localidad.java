package com.example.atencionMedica.dds.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Localidad")
public class Localidad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "partido_id", referencedColumnName = "id")
    private Partido partido;
}