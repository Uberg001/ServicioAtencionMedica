package com.example.atencionMedica.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

@Data
@Entity
@Table(name = "Documento")
public class Documento extends Persistente {

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private TipoDocumento tipo;

    @Column(name = "numero")
    private String numero;
}