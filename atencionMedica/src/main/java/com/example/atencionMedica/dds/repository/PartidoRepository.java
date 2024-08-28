package com.example.atencionMedica.dds.repository;

import com.example.atencionMedica.dds.entity.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
}