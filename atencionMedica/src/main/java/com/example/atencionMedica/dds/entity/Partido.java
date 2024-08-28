package com.example.atencionMedica.dds.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Partido")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id")
    private Provincia provincia;
}