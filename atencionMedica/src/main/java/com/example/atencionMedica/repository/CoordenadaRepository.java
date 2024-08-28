package com.example.atencionMedica.repository;

import com.example.atencionMedica.entity.Coordenada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadaRepository extends JpaRepository<Coordenada, Long> {
}