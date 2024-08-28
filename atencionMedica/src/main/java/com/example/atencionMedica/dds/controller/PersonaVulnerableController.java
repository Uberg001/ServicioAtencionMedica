package com.example.atencionMedica.dds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.atencionMedica.dds.entity.PersonaVulnerable;
import com.example.atencionMedica.dds.service.PersonaVulnerableService;



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
    public PersonaVulnerable createUser(@RequestBody PersonaVulnerable personaVulnerable) {
        return personaVulnerableService.createUser(personaVulnerable);
    }

    @PutMapping("/{id}")
    public PersonaVulnerable updateUser(@PathVariable("id") long userId, @RequestBody PersonaVulnerable personaVulnerable) {
        return personaVulnerableService.updateUser(userId, personaVulnerable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaVulnerable> deleteUser(@PathVariable("id") long userId) {
        personaVulnerableService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}