package com.example.atencionMedica.dds.service;


import com.example.atencionMedica.dds.dto.LocalidadReportDTO;
import com.example.atencionMedica.dds.entity.PersonaVulnerable;

import java.util.List;

public interface PersonaVulnerableService {
    List<PersonaVulnerable> getAllUsers();
    PersonaVulnerable getUserById(long userId);
    PersonaVulnerable createUser(PersonaVulnerable personaVulnerable);
    PersonaVulnerable updateUser(long userId, PersonaVulnerable personaVulnerable);
    void deleteUser(long userId);
    void deleteAllUsers();
    List<LocalidadReportDTO> obtenerReporteLocalidades();
}