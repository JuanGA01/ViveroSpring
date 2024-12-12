package com.example.tarea_3.repositorio;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tarea_3.modelo.Ejemplar;

public interface EjemplarRepositorio extends JpaRepository<Ejemplar, Long> {
	
	// Buscar ejemplares por nombre
    List<Ejemplar> findByNombre(String nombre); 
    
    // Buscar ejemplares de una planta específica
    List<Ejemplar> findByPlantaCodigo(String codigoPlanta);
    
    // Buscar ejemplares relacionados con una lista de códigos de plantas
    List<Ejemplar> findByPlantaCodigoIn(Set<String> codigosPlantas);
    
    // Buscar cuantos ejemplareshay a partir de un código de una planta
	Long countByPlantaCodigo(String codigo);
    
}