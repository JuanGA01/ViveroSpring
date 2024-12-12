package com.example.tarea_3.utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.tarea_3.modelo.Credenciales;
import com.example.tarea_3.modelo.Ejemplar;
import com.example.tarea_3.modelo.Mensaje;
import com.example.tarea_3.modelo.Persona;
import com.example.tarea_3.modelo.Planta;
import com.example.tarea_3.repositorio.EjemplarRepositorio;
import com.example.tarea_3.repositorio.MensajeRepositorio;
import com.example.tarea_3.repositorio.PersonaRepositorio;
import com.example.tarea_3.repositorio.PlantaRepositorio;
import java.time.LocalDateTime;

@Component
public class InicializadorBBDD implements CommandLineRunner {

    @Autowired
    private PlantaRepositorio plantaRepo;

    @Autowired
    private EjemplarRepositorio ejemplarRepo;

    @Autowired
    private PersonaRepositorio personaRepo;
    
    @Autowired
    private MensajeRepositorio mensajeRepo;
    
    @Override
    public void run(String... args) throws Exception {
        if (plantaRepo.count() == 0) {
            insertarDatosDePrueba();
        }
    }

    private void insertarDatosDePrueba() {
        // Plantas
        Planta planta1 = new Planta("PL001", "Maculís", "Tabebuia rosea");
        Planta planta2 = new Planta("PL002", "Helecho plateado", "Cyathea dealbata");
        Planta planta3 = new Planta("PL003", "Orquídea", "Phalaenopsis amabilis");
        plantaRepo.save(planta1);
        plantaRepo.save(planta2);
        plantaRepo.save(planta3);

        // Ejemplares
        Long countPlanta1 = ejemplarRepo.countByPlantaCodigo(planta1.getCodigo());
        Ejemplar ejemplar1 = new Ejemplar();
        ejemplar1.setPlanta(planta1);
        ejemplar1.setNombre(planta1.getCodigo() + "_" + (countPlanta1 + 1));
        ejemplarRepo.save(ejemplar1);

        Long countPlanta2 = ejemplarRepo.countByPlantaCodigo(planta2.getCodigo());
        Ejemplar ejemplar2 = new Ejemplar();
        ejemplar2.setPlanta(planta2);
        ejemplar2.setNombre(planta2.getCodigo() + "_" + (countPlanta2 + 1));
        ejemplarRepo.save(ejemplar2);

        Long countPlanta3 = ejemplarRepo.countByPlantaCodigo(planta3.getCodigo());
        Ejemplar ejemplar3 = new Ejemplar();
        ejemplar3.setPlanta(planta3);
        ejemplar3.setNombre(planta3.getCodigo() + "_" + (countPlanta3 + 1));
        ejemplarRepo.save(ejemplar3);


        // Persona y credenciales
        if (personaRepo.findByEmail("admin@admin.com").isEmpty()) {
            // Crear las credenciales
            Credenciales credencialesAdmin = new Credenciales();
            credencialesAdmin.setUsuario("admin");
            credencialesAdmin.setPassword("admin");

            // Crear la persona
            Persona admin = new Persona();
            admin.setNombre("administrador");
            admin.setEmail("admin@admin.com");

            // Configurar la relación bidireccional
            admin.setCrecenciales(credencialesAdmin);
            credencialesAdmin.setPersona(admin);

            // Guardar la persona
            personaRepo.save(admin);
        }

        // Mensajes
        Mensaje mensaje1 = new Mensaje();
        mensaje1.setFechahora(LocalDateTime.of(2024, 10, 24, 10, 30));
        mensaje1.setMensaje("El ejemplar está en buen estado");
        mensaje1.setEjemplar(ejemplar1);
        mensaje1.setPersona(personaRepo.findByEmail("admin@admin.com").get());
        mensajeRepo.save(mensaje1);

        Mensaje mensaje2 = new Mensaje();
        mensaje2.setFechahora(LocalDateTime.of(2024, 10, 24, 11, 0));
        mensaje2.setMensaje("El ejemplar necesita más agua");
        mensaje2.setEjemplar(ejemplar2);
        mensaje2.setPersona(personaRepo.findByEmail("admin@admin.com").get());
        mensajeRepo.save(mensaje2);

        System.out.println("Datos de prueba insertados en la BBDD creada");
    }

}
