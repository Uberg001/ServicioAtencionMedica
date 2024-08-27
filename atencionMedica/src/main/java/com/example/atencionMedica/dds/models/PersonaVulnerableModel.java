package com.example.atencionMedica.dds.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "persona_vulnerable")
public class PersonaVulnerableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column()
    private String nombre;

    @Column()
    private Boolean enCondicionDeCalle;

    @Column()
    private String domicilio;

    @Column()
    private String documento;

    @Column()
    private String tarjeta;
}
