package com.example.atencionMedica.dds.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Provincia")
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre")
    private String nombre;
}