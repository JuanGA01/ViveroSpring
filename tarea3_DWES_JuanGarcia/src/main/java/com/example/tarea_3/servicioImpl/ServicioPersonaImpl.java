package com.example.tarea_3.servicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tarea_3.modelo.Persona;
import com.example.tarea_3.repositorio.PersonaRepositorio;
import com.example.tarea_3.servicio.ServicioPersona;

@Service
public class ServicioPersonaImpl implements ServicioPersona {

    @Autowired
    private PersonaRepositorio personaRepo;
    
    @Override
    public Persona findById(Long id) {
        return personaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
    }

    @Override
    public Boolean CorreoExistente(Persona persona) {
        // Verificar si el correo ya existe en la base de datos
        return personaRepo.existsByEmail(persona.getEmail());
    }

    @Override
    public Long GuardarPersona(Persona persona) {
        // Verificar si el correo ya existe antes de guardar
        if (!CorreoExistente(persona)) {
            Persona personaGuardada = personaRepo.save(persona); // Guardar la entidad Persona
            return personaGuardada.getId(); // Devolver el ID de la persona guardada
        } else {
            throw new RuntimeException("El correo ya está registrado: " + persona.getEmail());
        }
        
    }

    
}
