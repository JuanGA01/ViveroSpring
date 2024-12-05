package com.example.tarea_3.servicioImpl;

import java.time.LocalDateTime;

import com.example.tarea_3.modelo.Ejemplar;
import com.example.tarea_3.modelo.Mensaje;
import com.example.tarea_3.modelo.Persona;
import com.example.tarea_3.modelo.Planta;
import com.example.tarea_3.servicio.ServicioMensaje;

public class ServicioMensajeImpl implements ServicioMensaje {

	@Override
	public String crearMensaje(Persona persona, Ejemplar ejemplar, Mensaje mensaje) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mensaje VerMensajesDeSeguimientoEjemplar(Ejemplar ejemplar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buscarMensajeXEjemplar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listarTodosMensajes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listarXPersona(Persona persona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listarXRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listarXTipoPlanta(Planta planta) {
		// TODO Auto-generated method stub
		return null;
	}

}
