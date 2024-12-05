package com.example.tarea_3.servicio;

import com.example.tarea_3.modelo.Persona;

public interface ServicioPersona {

    // Verifica si el correo de la persona ya existe en el sistema.
    Boolean CorreoExistente(Persona persona);

    // Guarda una nueva persona en el sistema y devuelve su ID.
    Long GuardarPersona(Persona persona);

}
