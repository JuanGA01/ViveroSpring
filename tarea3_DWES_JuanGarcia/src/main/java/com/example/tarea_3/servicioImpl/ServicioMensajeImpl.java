package com.example.tarea_3.servicioImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tarea_3.modelo.Ejemplar;
import com.example.tarea_3.modelo.Mensaje;
import com.example.tarea_3.modelo.Persona;
import com.example.tarea_3.modelo.Planta;
import com.example.tarea_3.repositorio.EjemplarRepositorio;
import com.example.tarea_3.repositorio.MensajeRepositorio;
import com.example.tarea_3.repositorio.PersonaRepositorio;
import com.example.tarea_3.servicio.ServicioMensaje;

@Service
public class ServicioMensajeImpl implements ServicioMensaje {

	    @Autowired
	    private MensajeRepositorio mensajeRepo;

	    @Autowired
	    private EjemplarRepositorio ejemplarRepo;

	    @Autowired
	    private PersonaRepositorio personaRepo;

	    @Override
	    public String crearMensaje(Persona persona, Ejemplar ejemplar, Mensaje mensaje) {
	        // Validar que la persona y el ejemplar existen
	        Persona personaExistente = personaRepo.findById(persona.getId())
	                .orElseThrow(() -> new RuntimeException("Persona con ID: " + persona.getId() + " no encontrada"));

	        Ejemplar ejemplarExistente = ejemplarRepo.findById(ejemplar.getId())
	                .orElseThrow(() -> new RuntimeException("Ejemplar con ID: " + ejemplar.getId() + " no encontrado"));

	        // Configurar y guardar el mensaje
	        mensaje.setPersona(personaExistente);
	        mensaje.setEjemplar(ejemplarExistente);
	        mensajeRepo.save(mensaje);

	        return "Mensaje creado correctamente";
	    }

	    @Override
	    public String buscarMensajeXEjemplar(Long id) {
	        List<Mensaje> mensajes = mensajeRepo.findByEjemplarId(id);

	        return mensajes.isEmpty()
	                ? "No hay mensajes para el ejemplar con ID:" + id
	                : formatMensajes(mensajes);
	    }

	    @Override
	    public String listarTodosMensajes() {
	        List<Mensaje> mensajes = mensajeRepo.findAll();

	        return mensajes.isEmpty()
	                ? "No hay mensajes registrados"
	                : formatMensajes(mensajes);
	    }

	    @Override
	    public String listarXPersona(Persona persona) {
	        List<Mensaje> mensajes = mensajeRepo.findByPersonaId(persona.getId());

	        return mensajes.isEmpty()
	                ? "No hay mensajes para la persona con ID:" + persona.getId()
	                : formatMensajes(mensajes);
	    }

	    @Override
	    public String listarXRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
	        List<Mensaje> mensajes = mensajeRepo.findByFechahoraBetween(desde, hasta);

	        return mensajes.isEmpty()
	                ? "No hay mensajes en el rango de fechas especificado"
	                : formatMensajes(mensajes);
	    }

	    @Override
	    public String listarXTipoPlanta(Planta planta) {
	        List<Ejemplar> ejemplares = ejemplarRepo.findByPlantaCodigo(planta.getCodigo());
	        if (ejemplares.isEmpty()) {
	            return "No hay ejemplares para la planta con código:" + planta.getCodigo();
	        }

	        List<Mensaje> mensajes = mensajeRepo.findByEjemplarIn(ejemplares);
	        return mensajes.isEmpty()
	                ? "No hay mensajes para los ejemplares de la planta con código:" + planta.getCodigo()
	                : formatMensajes(mensajes);
	    }

	    // Método auxiliar para formatear mensajes
	    private String formatMensajes(List<Mensaje> mensajes) {
	        return mensajes.stream()
	                .map(m -> m.getFechahora() + " - " + m.getMensaje() + " (Ejemplar ID: " + m.getEjemplar().getId() + ", Persona ID: " + m.getPersona().getId() + ")")
	                .collect(Collectors.joining("\n"));
	    }

}
