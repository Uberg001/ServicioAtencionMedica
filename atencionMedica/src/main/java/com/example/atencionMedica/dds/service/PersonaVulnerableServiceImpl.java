package com.example.atencionMedica.dds.service;

import com.example.atencionMedica.dds.entity.PersonaVulnerable;
import com.example.atencionMedica.dds.exception.*;
import com.example.atencionMedica.dds.repository.PersonaVulnerableRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaVulnerableServiceImpl implements PersonaVulnerableService {

    @Autowired
    private PersonaVulnerableRepository personaVulnerableRepository;

    @Override
    public List<PersonaVulnerable> getAllUsers() {
        return personaVulnerableRepository.findAll();
    }

    @Override
    public PersonaVulnerable getUserById(long userId) {
        return personaVulnerableRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
    }

    @Override
    public PersonaVulnerable createUser(PersonaVulnerable personaVulnerable) {
        return personaVulnerableRepository.save(personaVulnerable);
    }

    @Override
    public PersonaVulnerable updateUser(long userId, PersonaVulnerable personaVulnerable) {
        PersonaVulnerable existingPersonaVulnerable = personaVulnerableRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        existingPersonaVulnerable.setFirstName(personaVulnerable.getFirstName());
        existingPersonaVulnerable.setLastName(personaVulnerable.getLastName());
        existingPersonaVulnerable.setEmail(personaVulnerable.getEmail());
        return personaVulnerableRepository.save(existingPersonaVulnerable);
    }

    @Override
    public void deleteUser(long userId) {
        PersonaVulnerable existingPersonaVulnerable = personaVulnerableRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        personaVulnerableRepository.delete(existingPersonaVulnerable);
    }
}