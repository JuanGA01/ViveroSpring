package com.example.tarea_3.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tarea_3.modelo.Ejemplar;

public interface EjemplarRepositorio extends JpaRepository<Ejemplar, Long> {
	 // Métodos personalizados
    List<Ejemplar> findByNombre(String nombre); // Buscar ejemplares por nombre
    List<Ejemplar> findByPlantaCodigo(String codigoPlanta); // Buscar ejemplares de una planta específica
}