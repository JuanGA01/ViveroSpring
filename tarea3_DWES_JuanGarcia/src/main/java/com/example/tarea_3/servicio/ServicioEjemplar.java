package com.example.tarea_3.servicio;

import java.util.Set;
import com.example.tarea_3.modelo.Ejemplar;
import com.example.tarea_3.modelo.Persona;
import com.example.tarea_3.modelo.Planta;

public interface ServicioEjemplar {
    
    // Registra un nuevo ejemplar asociado a una planta y una persona.
    Ejemplar registrarNuevoEjemplar(Planta planta, Persona persona);
    
    // Lista los ejemplares que pertenecen a los tipos de planta proporcionados.
    String listarEjemplaresPorTipoDePlanta(Set<Planta> listaABuscar);
    
    // Lista todos los ejemplares disponibles.
    String listarEjemplares();
    
    // Verifica si un ejemplar con el ID proporcionado existe.
    boolean existeEjemplar(Long id);
    
}

