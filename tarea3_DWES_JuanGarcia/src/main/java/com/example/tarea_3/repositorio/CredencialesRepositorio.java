package com.example.tarea_3.repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tarea_3.modelo.Credenciales;

public interface CredencialesRepositorio extends JpaRepository<Credenciales, Long> {
    
	// Buscar por usuario
    Optional<Credenciales> findByUsuario(String usuario);
    
    // Verificar si el usuario ya existe
    boolean existsByUsuario(String usuario); 
    
}
