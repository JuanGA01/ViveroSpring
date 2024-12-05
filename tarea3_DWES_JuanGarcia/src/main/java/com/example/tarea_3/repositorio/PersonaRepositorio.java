package com.example.tarea_3.repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tarea_3.modelo.Persona;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
    // Métodos personalizados
    Optional<Persona> findByEmail(String email); // Buscar por email
}