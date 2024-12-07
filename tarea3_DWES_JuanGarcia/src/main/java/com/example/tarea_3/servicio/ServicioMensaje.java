package com.example.tarea_3.servicio;

import java.time.LocalDateTime;
import com.example.tarea_3.modelo.Ejemplar;
import com.example.tarea_3.modelo.Mensaje;
import com.example.tarea_3.modelo.Persona;
import com.example.tarea_3.modelo.Planta;

public interface ServicioMensaje {

    // Crea un nuevo mensaje asociado a una persona y un ejemplar.
    String crearMensaje(Persona persona, Ejemplar ejemplar, Mensaje mensaje);

    // Busca un mensaje específico por el ID del ejemplar.
    String buscarMensajeXEjemplar(Long id);

    // Lista todos los mensajes disponibles en el sistema.
    String listarTodosMensajes();

    // Lista todos los mensajes asociados a una persona específica.
    String listarXPersona(Persona persona);

    // Lista los mensajes que se encuentran dentro de un rango de fechas determinado.
    String listarXRangoFechas(LocalDateTime desde, LocalDateTime hasta);

    // Lista los mensajes relacionados con una planta específica.
    String listarXTipoPlanta(Planta planta);

}
