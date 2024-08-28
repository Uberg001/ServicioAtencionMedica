// src/main/java/com/example/atencionMedica/dds/entity/PersonaVulnerable.java
package com.example.atencionMedica.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

@Data
@Entity
@Table(name = "PersonaVulnerable")
public class PersonaVulnerable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documento_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Documento documento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Direccion direccion;
}