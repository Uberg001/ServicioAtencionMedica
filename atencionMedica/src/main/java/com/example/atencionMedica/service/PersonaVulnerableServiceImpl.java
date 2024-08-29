// src/main/java/com/example/atencionMedica/dds/service/PersonaVulnerableServiceImpl.java
package com.example.atencionMedica.service;

import com.example.atencionMedica.dto.DireccionDTOEntrada;
import com.example.atencionMedica.dto.LocalidadReportDTOSalida;
import com.example.atencionMedica.dto.PersonaVulnerableDTOEntrada;
import com.example.atencionMedica.entity.*;
import com.example.atencionMedica.repository.*;
import com.example.atencionMedica.exception.ResourceNotFoundException;

import org.modelmapper.ModelMapper;
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

//    @Autowired
//    private LocalidadRepository localidadRepository;
//
//    @Autowired
//    private PartidoRepository partidoRepository;
//
//    @Autowired
//    private ProvinciaRepository provinciaRepository;
//
//    @Autowired
//    private CoordenadaRepository coordenadaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<PersonaVulnerable> getAllUsers() {
        return personaVulnerableRepository.findAll();
    }

    @Override
    public PersonaVulnerable getUserById(long userId) {
        return personaVulnerableRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id :" + userId));
    }

    @Override
    public PersonaVulnerable createUser(PersonaVulnerableDTOEntrada personaVulnerableDTOEntrada) {

        PersonaVulnerable personaVulnerable = convertPersonaDTOToEntity(personaVulnerableDTOEntrada);
        return personaVulnerableRepository.save(personaVulnerable);

    }

    @Override
    public PersonaVulnerable updateUser(long userId, PersonaVulnerableDTOEntrada personaVulnerableDTOEntrada) {

        personaVulnerableRepository.findById(userId).ifPresent(personaVulnerable -> {
            personaVulnerable.setFirstName(personaVulnerableDTOEntrada.getFirstName());
            personaVulnerable.setLastName(personaVulnerableDTOEntrada.getLastName());
            personaVulnerable.setEmail(personaVulnerableDTOEntrada.getEmail());
            Documento documento = modelMapper.map(personaVulnerableDTOEntrada.getDocumentoDTOEntrada(), Documento.class);
            personaVulnerable.setDocumento(documento);
            Direccion domicilio = convertDireccionDtoToEntity(personaVulnerableDTOEntrada.getDomicilioDTOEntrada());
            personaVulnerable.setDireccion(domicilio);
            personaVulnerableRepository.save(personaVulnerable);
        });
        return personaVulnerableRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id :" + userId));
    }

    @Override
    public void deleteUser(long userId) {
        PersonaVulnerable existingPersonaVulnerable = personaVulnerableRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id :" + userId));
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
                .collect(Collectors.groupingBy(p -> p.getDireccion().getLocalidad().getNombre()));

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
    public void ejecutarReporteDiario() {
        logger.info("Ejecutando reporte");
        obtenerReporteLocalidades();
    }

    private PersonaVulnerable convertPersonaDTOToEntity(PersonaVulnerableDTOEntrada personaVulnerableDTOEntrada) {
        PersonaVulnerable personaVulnerable = modelMapper.map(personaVulnerableDTOEntrada, PersonaVulnerable.class);

        Documento documento = modelMapper.map(personaVulnerableDTOEntrada.getDocumentoDTOEntrada(), Documento.class);

        Direccion domicilio = modelMapper.map(personaVulnerableDTOEntrada.getDomicilioDTOEntrada(), Direccion.class);

        Coordenada coordenada = modelMapper.map(personaVulnerableDTOEntrada.getDomicilioDTOEntrada()
                .getCoordenadaDTOEntrada(), Coordenada.class);

        Localidad localidad = modelMapper.map(personaVulnerableDTOEntrada.getDomicilioDTOEntrada()
                .getLocalidadDTOEntrada(), Localidad.class);

        Partido partido = modelMapper.map(personaVulnerableDTOEntrada.getDomicilioDTOEntrada()
                .getLocalidadDTOEntrada().getPartidoDTOEntrada(), Partido.class);

        Provincia provincia = modelMapper.map(personaVulnerableDTOEntrada.getDomicilioDTOEntrada()
                .getLocalidadDTOEntrada().getPartidoDTOEntrada().getProvinciaDTOEntrada(), Provincia.class);

        personaVulnerable.setDocumento(documento);
        domicilio.setCoordenada(coordenada);
        domicilio.setLocalidad(localidad);
        localidad.setPartido(partido);
        partido.setProvincia(provincia);
        personaVulnerable.setDireccion(domicilio);

        return personaVulnerable;
    }

    private Direccion convertDireccionDtoToEntity(DireccionDTOEntrada direccionDTOEntrada) {

        Direccion direccion = modelMapper.map(direccionDTOEntrada, Direccion.class);
        Coordenada coordenada = modelMapper.map(direccionDTOEntrada.getCoordenadaDTOEntrada(), Coordenada.class);
        Localidad localidad = modelMapper.map(direccionDTOEntrada.getLocalidadDTOEntrada(), Localidad.class);
        Partido partido = modelMapper.map(direccionDTOEntrada.getLocalidadDTOEntrada()
                .getPartidoDTOEntrada(), Partido.class);
        Provincia provincia = modelMapper.map(direccionDTOEntrada.getLocalidadDTOEntrada().getPartidoDTOEntrada()
                .getProvinciaDTOEntrada(), Provincia.class);

        direccion.setCoordenada(coordenada);
        direccion.setLocalidad(localidad);
        localidad.setPartido(partido);
        partido.setProvincia(provincia);

        return direccion;
    }
}