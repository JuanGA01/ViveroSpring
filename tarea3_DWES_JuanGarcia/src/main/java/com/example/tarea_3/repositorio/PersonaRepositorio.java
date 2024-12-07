package com.example.tarea_3.repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tarea_3.modelo.Persona;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
	
	//Método para buscar a una persona por su mail
    Optional<Persona> findByEmail(String email);
    
    // Método para verificar si un correo electrónico ya está registrado
    boolean existsByEmail(String email);
    
}