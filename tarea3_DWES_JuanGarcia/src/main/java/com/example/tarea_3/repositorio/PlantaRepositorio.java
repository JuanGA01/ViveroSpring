package com.example.tarea_3.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tarea_3.modelo.Planta;

public interface PlantaRepositorio extends JpaRepository<Planta, String> {
    
}