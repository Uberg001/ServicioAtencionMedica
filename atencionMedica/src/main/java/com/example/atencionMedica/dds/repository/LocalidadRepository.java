package com.example.atencionMedica.dds.repository;

import com.example.atencionMedica.dds.entity.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Long> {
}