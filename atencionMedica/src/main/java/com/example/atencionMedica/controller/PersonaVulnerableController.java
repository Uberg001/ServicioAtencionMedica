package com.example.atencionMedica.controller;

import java.util.List;

import com.example.atencionMedica.dto.PersonaVulnerableDTOEntrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.atencionMedica.entity.PersonaVulnerable;
import com.example.atencionMedica.service.PersonaVulnerableService;
import com.example.atencionMedica.dto.LocalidadReportDTOSalida;


@RestController
@RequestMapping("/api/ServicioAtencionMedica")
public class PersonaVulnerableController {

    @Autowired
    private PersonaVulnerableService personaVulnerableService;

    @GetMapping
    public List<PersonaVulnerable> getAllUsers() {
        return personaVulnerableService.getAllUsers();
    }

    @GetMapping("/{id}")
    public PersonaVulnerable getUserById(@PathVariable(value = "id") long userId) {
        return personaVulnerableService.getUserById(userId);
    }

    @PostMapping("/create")
    public PersonaVulnerable createUser(@RequestBody PersonaVulnerableDTOEntrada personaVulnerableDTOEntrada) {
        return personaVulnerableService.createUser(personaVulnerableDTOEntrada);
    }

    @PutMapping("/{id}")
    public PersonaVulnerable updateUser(@PathVariable("id") long userId, @RequestBody PersonaVulnerableDTOEntrada personaVulnerableDTOEntrada) {
        return personaVulnerableService.updateUser(userId, personaVulnerableDTOEntrada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaVulnerable> deleteUser(@PathVariable("id") long userId) {
        personaVulnerableService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllUsers() {
        personaVulnerableService.deleteAllUsers();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/reporteLocalidades")
    public List<LocalidadReportDTOSalida> obtenerReporteLocalidades() {
        return personaVulnerableService.obtenerReporteLocalidades();
    }
}