package com.example.atencionMedica.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Embeddable
public class Provincia extends Persistente{

    @Column(name = "nombre")
    private String nombre;
}