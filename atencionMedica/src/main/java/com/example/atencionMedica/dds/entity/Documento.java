package com.example.atencionMedica.dds.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoDocumento tipo;

    @Column(name = "numero")
    private String numero;
}