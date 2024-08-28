// src/main/java/com/example/atencionMedica/dds/service/PersonaVulnerableServiceImpl.java
package com.example.atencionMedica.dds.service;

import com.example.atencionMedica.dds.entity.*;
import com.example.atencionMedica.dds.exception.*;
import com.example.atencionMedica.dds.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaVulnerableServiceImpl implements PersonaVulnerableService {

    @Autowired
    private PersonaVulnerableRepository personaVulnerableRepository;

    @Autowired
    private LocalidadRepository localidadRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private CoordenadaRepository coordenadaRepository;

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
        saveRelatedEntities(personaVulnerable);
        return personaVulnerableRepository.save(personaVulnerable);
    }

    @Override
    public PersonaVulnerable updateUser(long userId, PersonaVulnerable personaVulnerable) {
        PersonaVulnerable existingPersonaVulnerable = personaVulnerableRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        saveRelatedEntities(personaVulnerable);
        existingPersonaVulnerable.setFirstName(personaVulnerable.getFirstName());
        existingPersonaVulnerable.setLastName(personaVulnerable.getLastName());
        existingPersonaVulnerable.setEmail(personaVulnerable.getEmail());
        existingPersonaVulnerable.setDocumento(personaVulnerable.getDocumento());
        existingPersonaVulnerable.setDomicilio(personaVulnerable.getDomicilio());
        return personaVulnerableRepository.save(existingPersonaVulnerable);
    }

    @Override
    public void deleteUser(long userId) {
        PersonaVulnerable existingPersonaVulnerable = personaVulnerableRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        personaVulnerableRepository.delete(existingPersonaVulnerable);
    }

    private void saveRelatedEntities(PersonaVulnerable personaVulnerable) {
        Direccion direccion = personaVulnerable.getDomicilio();
        if (direccion != null) {
            Localidad localidad = direccion.getLocalidad();
            if (localidad != null) {
                Partido partido = localidad.getPartido();
                if (partido != null) {
                    Provincia provincia = partido.getProvincia();
                    if (provincia != null) {
                        provinciaRepository.save(provincia);
                    }
                    partidoRepository.save(partido);
                }
                localidadRepository.save(localidad);
            }
            Coordenada coordenada = direccion.getCoordenada();
            if (coordenada != null) {
                coordenadaRepository.save(coordenada);
            }
        }
    }
}