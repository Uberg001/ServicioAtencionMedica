package com.example.atencionMedica.dds.controllers;
import com.example.atencionMedica.dds.models.PersonaVulnerableModel;
import com.example.atencionMedica.dds.services.PersonaVulnerableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaVulnerableController {

    @Autowired
    public PersonaVulnerableService personaVulnerableService;

    @GetMapping()
    public Optional<PersonaVulnerableModel> getPersonasVulnerables() {
        long id = 1;
        return personaVulnerableService.getPersonas(id);
    }

    @PostMapping()
    public PersonaVulnerableModel savePersonaVulnerable(@RequestBody PersonaVulnerableModel personaVulnerableModel){
        return personaVulnerableService.savePersonaVulnerable(personaVulnerableModel);
    }

}