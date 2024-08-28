package com.example.atencionMedica.service;


import com.example.atencionMedica.dto.LocalidadReportDTOSalida;
import com.example.atencionMedica.dto.PersonaVulnerableDTOEntrada;
import com.example.atencionMedica.entity.PersonaVulnerable;

import java.util.List;

public interface PersonaVulnerableService {
    List<PersonaVulnerable> getAllUsers();
    PersonaVulnerable getUserById(long userId);
    PersonaVulnerable createUser(PersonaVulnerableDTOEntrada personaVulnerableDTOEntrada);
    PersonaVulnerable updateUser(long userId, PersonaVulnerableDTOEntrada personaVulnerableDTOEntrada);
    void deleteUser(long userId);
    void deleteAllUsers();
    List<LocalidadReportDTOSalida> obtenerReporteLocalidades();
}