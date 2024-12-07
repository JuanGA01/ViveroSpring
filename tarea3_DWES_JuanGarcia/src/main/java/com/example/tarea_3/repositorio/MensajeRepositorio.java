package com.example.tarea_3.repositorio;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tarea_3.modelo.Ejemplar;
import com.example.tarea_3.modelo.Mensaje;

public interface MensajeRepositorio extends JpaRepository<Mensaje, Long> {
	
    // Buscar mensajes por ID de ejemplar
    List<Mensaje> findByEjemplarId(Long ejemplarId);

    // Buscar mensajes por ID de persona
    List<Mensaje> findByPersonaId(Long personaId);

    // Buscar mensajes por rango de fechas
    List<Mensaje> findByFechahoraBetween(LocalDateTime desde, LocalDateTime hasta);

    // Buscar mensajes asociados a una lista de ejemplares
    List<Mensaje> findByEjemplarIn(List<Ejemplar> ejemplares);
    
}