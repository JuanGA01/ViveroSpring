package com.example.tarea_3.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tarea_3.modelo.Mensaje;

public interface MensajeRepositorio extends JpaRepository<Mensaje, Long> {
    // Métodos personalizados
    List<Mensaje> findByIdEjemplar(Long idEjemplar); // Buscar mensajes asociados a un ejemplar
    List<Mensaje> findByIdPersona(Long idPersona); // Buscar mensajes enviados por una persona
}