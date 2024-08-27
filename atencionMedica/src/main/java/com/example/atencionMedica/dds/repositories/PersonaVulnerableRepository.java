package com.example.atencionMedica.dds.repositories;


import com.example.atencionMedica.dds.models.PersonaVulnerableModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaVulnerableRepository extends JpaRepository<PersonaVulnerableModel, Long> {
    PersonaVulnerableModel findById(long id);
}
