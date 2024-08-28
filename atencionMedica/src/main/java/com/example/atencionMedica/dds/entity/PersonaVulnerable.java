// src/main/java/com/example/atencionMedica/dds/entity/PersonaVulnerable.java
package com.example.atencionMedica.dds.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "PersonaVulnerable")
public class PersonaVulnerable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documento_id", referencedColumnName = "id")
    private Documento documento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Direccion domicilio;
}