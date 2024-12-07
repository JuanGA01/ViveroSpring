package com.example.tarea_3.servicioImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tarea_3.modelo.Ejemplar;
import com.example.tarea_3.modelo.Persona;
import com.example.tarea_3.modelo.Planta;
import com.example.tarea_3.repositorio.EjemplarRepositorio;
import com.example.tarea_3.repositorio.PersonaRepositorio;
import com.example.tarea_3.repositorio.PlantaRepositorio;
import com.example.tarea_3.servicio.ServicioEjemplar;

@Service
public class ServicioEjemplarImpl implements ServicioEjemplar {

	@Autowired
    private EjemplarRepositorio ejemplarRepo;

    @Autowired
    private PlantaRepositorio plantaRepo;

    @Autowired
    private PersonaRepositorio personaRepo;

    @Override
    public boolean registrarNuevoEjemplar(Planta planta, Persona persona) {
        // Verificar si la planta existe
        Planta plantaExistente = plantaRepo.findById(planta.getCodigo())
                .orElseThrow(() -> new RuntimeException("Planta no encontrada con código: " + planta.getCodigo()));

        // Verificar si la persona existe
        personaRepo.findById(persona.getId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + persona.getId()));

        // Crear un nuevo ejemplar
        Ejemplar nuevoEjemplar = new Ejemplar();
        nuevoEjemplar.setNombre("Ejemplar de " + plantaExistente.getNombreComun());
        nuevoEjemplar.setPlanta(plantaExistente);

        // Guardar el ejemplar en el repositorio
        ejemplarRepo.save(nuevoEjemplar);

        return true;
    }

    @Override
    public String listarEjemplaresPorTipoDePlanta(Set<Planta> listaABuscar) {
        // Obtener los códigos de las plantas a buscar
        Set<String> codigosPlantas = listaABuscar.stream()
                .map(Planta::getCodigo)
                .collect(Collectors.toSet());

        // Buscar ejemplares relacionados con esas plantas
        List<Ejemplar> ejemplares = ejemplarRepo.findByPlantaCodigoIn(codigosPlantas);

        if (ejemplares.isEmpty()) {
            return "No hay ejemplares para los tipos de plantas especificados.";
        }

        // Formatear la lista de ejemplares
        return ejemplares.stream()
                .map(e -> e.getNombre() + " - Planta: " + e.getPlanta().getNombreComun())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String listarEjemplares() {
        // Obtener todos los ejemplares
        List<Ejemplar> ejemplares = ejemplarRepo.findAll();

        if (ejemplares.isEmpty()) {
            return "No hay ejemplares registrados.";
        }

        // Formatear la lista de ejemplares
        return ejemplares.stream()
                .map(e -> e.getId() + ": " + e.getNombre() + " - Código de Planta: " + e.getPlanta().getCodigo())
                .collect(Collectors.joining("\n"));
    }


    @Override
    public boolean existeEjemplar(Long id) {
        // Verificar si existe un ejemplar con el ID proporcionado
        return ejemplarRepo.existsById(id);
    }

}
