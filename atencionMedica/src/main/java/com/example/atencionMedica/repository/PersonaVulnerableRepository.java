package com.example.atencionMedica.repository;

import com.example.atencionMedica.entity.PersonaVulnerable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonaVulnerableRepository extends JpaRepository<PersonaVulnerable, Long>{

}
