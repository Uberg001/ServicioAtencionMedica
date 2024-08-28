// src/main/java/com/example/atencionMedica/dds/service/PersonaVulnerableServiceImpl.java
package com.example.atencionMedica.dds.service;

import com.example.atencionMedica.dds.dto.LocalidadReportDTO;
import com.example.atencionMedica.dds.entity.*;
import com.example.atencionMedica.dds.exception.*;
import com.example.atencionMedica.dds.repository.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonaVulnerableServiceImpl implements PersonaVulnerableService {
    private static final Logger logger = LoggerFactory.getLogger(PersonaVulnerableService.class);

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

    @Override
    public void deleteAllUsers() {
        personaVulnerableRepository.deleteAll();
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

    @Override
    public List<LocalidadReportDTO> obtenerReporteLocalidades() {
        List<PersonaVulnerable> personas = personaVulnerableRepository.findAll();
        Map<String, List<PersonaVulnerable>> personasPorLocalidad = personas.stream()
                .collect(Collectors.groupingBy(p -> p.getDomicilio().getLocalidad().getNombre()));

        List<LocalidadReportDTO> reporte = new ArrayList<>();
        for (Map.Entry<String, List<PersonaVulnerable>> entry : personasPorLocalidad.entrySet()) {
            String localidadNombre = entry.getKey();
            List<PersonaVulnerable> personasEnLocalidad = entry.getValue();
            long cantidadPersonas = personasEnLocalidad.size();
            List<String> nombresPersonas = personasEnLocalidad.stream()
                    .map(p -> p.getFirstName() + " " + p.getLastName())
                    .collect(Collectors.toList());
            reporte.add(new LocalidadReportDTO(localidadNombre, cantidadPersonas, nombresPersonas));
        }
        return reporte;
    }

    //Metodo para la ejecucion diaria del servicio
    @Scheduled(cron = "0 * * * * *")
    public void ejecutarReporteDiario(){
        logger.info("Ejecutando reporte");
        obtenerReporteLocalidades();
    }
}