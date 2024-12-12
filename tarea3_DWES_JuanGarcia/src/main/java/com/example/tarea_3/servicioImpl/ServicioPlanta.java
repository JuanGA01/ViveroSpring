package com.example.tarea_3.servicioImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tarea_3.modelo.Planta;
import com.example.tarea_3.repositorio.PlantaRepositorio;

@Service
public class ServicioPlanta implements com.example.tarea_3.servicio.ServicioPlanta {

	@Autowired
    private PlantaRepositorio plantaRepo;

    @Override
    public String listaPlantas() {
        // Obtener todas las plantas en la base de datos
        List<Planta> plantas = plantaRepo.findAll();
        if (plantas.isEmpty()) {
            return "No hay plantas registradas";
        }
        return plantas.stream()
                .map(planta -> planta.getCodigo() + " - " + planta.getNombreComun())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String InsertarPlanta(Planta planta) {
        // Verificar si ya existe una planta con el mismo código
        Optional<Planta> plantaExistenteOpt = plantaRepo.findById(planta.getCodigo());
        
        if (plantaExistenteOpt.isPresent()) {
            // Modificar la planta existente
            Planta plantaExistente = plantaExistenteOpt.get();
            plantaExistente.setNombreComun(planta.getNombreComun());
            plantaExistente.setNombreCientifico(planta.getNombreCientifico());
            plantaRepo.save(plantaExistente); // Guardar cambios
            return "Planta " + plantaExistente.getCodigo() + " modificada correctamente" ;
        } else {
            // Guardar la nueva planta
            plantaRepo.save(planta);
            return "Planta insertada correctamente";
        }
    }


    @Override
    public boolean existePlanta(Planta planta) {
        // Verificar si existe una planta por su código
        return plantaRepo.existsById(planta.getCodigo());
    }

    @Override
    public Planta BuscarPlantaXId(Planta planta) {
        // Buscar una planta por su código
        return plantaRepo.findById(planta.getCodigo())
                .orElseThrow(() -> new RuntimeException("No se encontró una planta con el código " + planta.getCodigo()));
    }
    
}
