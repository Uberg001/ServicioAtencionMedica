package com.example.atencionMedica.dds.repository;

import com.example.atencionMedica.dds.entity.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
}