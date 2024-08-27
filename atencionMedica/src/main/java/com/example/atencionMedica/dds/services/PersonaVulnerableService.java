package com.example.atencionMedica.dds.services;

import com.example.atencionMedica.dds.models.PersonaVulnerableModel;
import com.example.atencionMedica.dds.repositories.PersonaVulnerableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaVulnerableService {

    @Autowired
    PersonaVulnerableRepository personaVulnerableRepository;

    public Optional<PersonaVulnerableModel> getPersonas(Long id){
        return personaVulnerableRepository.findById(id);
    }

    public PersonaVulnerableModel savePersonaVulnerable(PersonaVulnerableModel personaVulnerableModel){
        return personaVulnerableRepository.save(personaVulnerableModel);
    }

}
