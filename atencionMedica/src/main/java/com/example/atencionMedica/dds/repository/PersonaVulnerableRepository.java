package com.example.atencionMedica.dds.repository;

import com.example.atencionMedica.dds.entity.PersonaVulnerable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonaVulnerableRepository extends JpaRepository<PersonaVulnerable, Long>{

}
