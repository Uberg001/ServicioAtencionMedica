package com.example.atencionMedica.dds.repository;

import com.example.atencionMedica.dds.entity.Coordenada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadaRepository extends JpaRepository<Coordenada, Long> {
}