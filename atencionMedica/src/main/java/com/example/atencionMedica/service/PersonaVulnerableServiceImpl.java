// src/main/java/com/example/atencionMedica/dds/service/PersonaVulnerableServiceImpl.java
package com.example.atencionMedica.service;

import com.example.atencionMedica.dto.DireccionDTOEntrada;
import com.example.atencionMedica.dto.DocumentoDTOEntrada;
import com.example.atencionMedica.dto.LocalidadReportDTOSalida;
import com.example.atencionMedica.dto.PersonaVulnerableDTOEntrada;
import com.example.atencionMedica.entity.*;
import com.example.atencionMedica.repository.*;
import com.example.atencionMedica.exception.ResourceNotFoundException;

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
    public PersonaVulnerable createUser(PersonaVulnerableDTOEntrada personaVulnerableDTOEntrada) {
        PersonaVulnerable personaVulnerable = new PersonaVulnerable();
        personaVulnerable.setFirstName(personaVulnerableDTOEntrada.getFirstName());
        personaVulnerable.setLastName(personaVulnerableDTOEntrada.getLastName());
        personaVulnerable.setEmail(personaVulnerableDTOEntrada.getEmail());

        DocumentoDTOEntrada documentoDTOEntrada = personaVulnerableDTOEntrada.getDocumentoDTOEntrada();
        Documento documento = new Documento();
        documento.setTipo(TipoDocumento.valueOf(documentoDTOEntrada.getTipo()));

        DireccionDTOEntrada domicilioDTOEntrada = personaVulnerableDTOEntrada.getDomicilioDTOEntrada();
        Direccion domicilio = new Direccion();
        domicilio.setCalle(domicilioDTOEntrada.getCalle());
        domicilio.setAltura(domicilioDTOEntrada.getAltura());

        Coordenada coordenada = new Coordenada();
        coordenada.setLatitud(domicilioDTOEntrada.getCoordenadaDTOEntrada().getLatitud());
        coordenada.setLongitud(domicilioDTOEntrada.getCoordenadaDTOEntrada().getLongitud());
        domicilio.setCoordenada(coordenada);

        Localidad localidad = new Localidad();
        localidad.setNombre(domicilioDTOEntrada.getLocalidadDTOEntrada().getNombre());

        Partido partido = new Partido();
        partido.setNombre(domicilioDTOEntrada.getLocalidadDTOEntrada().getPartidoDTOEntrada().getNombre());

        Provincia provincia = new Provincia();
        provincia.setNombre(domicilioDTOEntrada.getLocalidadDTOEntrada().getPartidoDTOEntrada().getProvinciaDTOEntrada().getNombre());

        personaVulnerable.setDocumento(documento);
        personaVulnerable.setDireccion(domicilio);
        domicilio.setLocalidad(localidad);
        localidad.setPartido(partido);
        partido.setProvincia(provincia);
        domicilio.setCoordenada(coordenada);
        documento.setTipo(TipoDocumento.valueOf(documentoDTOEntrada.getTipo()));

        return personaVulnerableRepository.save(personaVulnerable);
    }

    @Override
    public PersonaVulnerable updateUser(long userId, PersonaVulnerableDTOEntrada personaVulnerableDTOEntrada) {
        PersonaVulnerable existingPersonaVulnerable = personaVulnerableRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        existingPersonaVulnerable.setFirstName(personaVulnerableDTOEntrada.getFirstName());
        existingPersonaVulnerable.setLastName(personaVulnerableDTOEntrada.getLastName());
        existingPersonaVulnerable.setEmail(personaVulnerableDTOEntrada.getEmail());
        //        existingPersonaVulnerable.setDocumento(personaVulnerableDTOEntrada.getDocumento());
        //        existingPersonaVulnerable.setDireccion(personaVulnerableDTOEntrada.getDomicilio());
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


    @Override
    public List<LocalidadReportDTOSalida> obtenerReporteLocalidades() {
        List<PersonaVulnerable> personas = personaVulnerableRepository.findAll();
        Map<String, List<PersonaVulnerable>> personasPorLocalidad = personas.stream()
                .collect(Collectors.groupingBy(p -> p.getDomicilio().getLocalidad().getNombre()));

        List<LocalidadReportDTOSalida> reporte = new ArrayList<>();
        for (Map.Entry<String, List<PersonaVulnerable>> entry : personasPorLocalidad.entrySet()) {
            String localidadNombre = entry.getKey();
            List<PersonaVulnerable> personasEnLocalidad = entry.getValue();
            long cantidadPersonas = personasEnLocalidad.size();
            List<String> nombresPersonas = personasEnLocalidad.stream()
                    .map(p -> p.getFirstName() + " " + p.getLastName())
                    .collect(Collectors.toList());
            reporte.add(new LocalidadReportDTOSalida(localidadNombre, cantidadPersonas, nombresPersonas));
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