package com.example.atencionMedica.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class Persistente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "activo")
    protected boolean activo;

    @Setter
    @Column(name = "fechaCreacion")
    protected LocalDateTime fechaCreacion;

    @Setter
    @Column(name = "fechaModificacion", nullable = true)
    protected LocalDateTime fechaModificacion = null;

    @Setter
    @Column(name = "fechaBaja", nullable = true)
    protected LocalDateTime fechaBaja = null;
}