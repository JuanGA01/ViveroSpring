package com.example.tarea_3.facade;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.tarea_3.modelo.Credenciales;
import com.example.tarea_3.modelo.Ejemplar;
import com.example.tarea_3.modelo.Mensaje;
import com.example.tarea_3.modelo.Persona;
import com.example.tarea_3.modelo.Planta;
import com.example.tarea_3.servicio.ServicioCredenciales;
import com.example.tarea_3.servicio.ServicioEjemplar;
import com.example.tarea_3.servicio.ServicioMensaje;
import com.example.tarea_3.servicio.ServicioPersona;
import com.example.tarea_3.servicio.ServicioPlanta;
import com.example.tarea_3.utilidades.Utilities;

@Component
public class ViveroFacade {

	//El portal es estático
	private static ViveroFacade portal;

	//El scaner lo reciclaré para cada interacción con el usuario
	Scanner scanner = new Scanner(System.in);

	//Servicios
	@Autowired
	ServicioPlanta plantServ;
	
	@Autowired
	ServicioCredenciales credencialesServ;
	 
	@Autowired
	ServicioPersona personaServ;
	
	@Autowired
	ServicioEjemplar ejemplarServ;
	
	@Autowired
	ServicioMensaje mensajeServ;

	//Método para instanciar el portal
	public static ViveroFacade getPortal() {
		if (portal==null)
			portal=new ViveroFacade();
		return portal;
	}


	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//A partir de aquí están todos los Menús principales
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////


	//Menú principal
	public void mostrarMenuGestionPrincipal() {
		int option;
		do {	        
			System.out.println("\n----🌱 Vivero 🌱 ----");
			System.out.println("1. Ver Plantas (Invitado) 🌷🌹🌺🌻🌼");
			System.out.println("2. Autenticarse 👨‍🦰👩👩‍🦰👨‍🦳👨‍");
			System.out.println("3. Salir 👋👋👋👋👋👋");
			System.out.print("Seleccione una opción: ");
			option = Utilities.pedirEntero(scanner.nextLine(), scanner);
			switch (option) {
			case 1:
				System.out.println("-----------------------------------------");
				System.out.println(plantServ.listaPlantas());
				break;
			case 2:
				login();
				break;
			case 3:
				System.out.println("Saliendo del sistema...");
				break;
			default:
				System.out.println("⚠️ Opción inválida, inserte un valor válido ⚠️");
			}
		} while (option != 3);
		scanner.close();
	}


	//Menú admin
	private void menuAdmin(Persona persona) {
		int option;
		do {
			System.out.println("\n---- 🚷 Menu Admin 🚷 ----");
			System.out.println("1. Ver Plantas 🌷🌹🌺🌻🌼");
			System.out.println("2. Registrar usuarios 🆕🆕👨‍🦰👩👩‍🦰👨‍🦳👨🆕🆕");
			System.out.println("3. Insertar/modificar planta 🆕🔄🌷🌹🌺🌻🌼🔄🆕");
			System.out.println("4. Registrar un nuevo ejemplar ➕➕🌷🌹🌺");
			System.out.println("5. Filtrar ejemplares por tipo de planta 🌷🌹🌺🌻🌼");
			System.out.println("6. Ver mensajes de seguimiento de un ejemplar 💬💬💬💬💬");
			System.out.println("7. Añadir mensajes ➕💬➕💬➕");
			System.out.println("8. Ver mensajes 💬💬💬💬💬");
			System.out.println("9. Salir al menú principal 🔙🔙🔙🔙🔙");
			System.out.print("Seleccione una opción: ");
			option = Utilities.pedirEntero(scanner.nextLine(), scanner);
			switch (option) {
			case 1:
				System.out.println("-----------------------------------------");
				System.out.println(plantServ.listaPlantas());
				break;
			case 2:
				System.out.println("-----------------------------------------");
				registroUsuarios();
				break;
			case 3:
				System.out.println("-----------------------------------------");
				System.out.println("Plantas: ");
				System.out.println(plantServ.listaPlantas());
				insertarPlanta();
				break;
			case 4:
				System.out.println("-----------------------------------------");
				registrarEjemplar(persona);
				break;
			case 5:
				System.out.println("-----------------------------------------");
				buscarEjemplaresXtipoDePlanta();
				break;
			case 6:
				System.out.println("-----------------------------------------");
				VerMensajesSeguimientoEjemplar();
				break;
			case 7:
				añadirMensajes(persona);
				break;
			case 8:
				menuMensajesPersona(persona);
				break;
			case 9:
				System.out.println("Regresando al menú principal...");
				break;
			default:
				System.out.println("⚠️ Opción inválida, inserte un valor válido ⚠️");
			}
		} while (option != 9);
	}


	//Menú para los no administradores
	private void menuPersonal(Persona persona) {
		int option;
		do {
			System.out.println("\n---- Menu Personal ----");
			System.out.println("1. Ver Plantas 🌷🌹🌺🌻🌼");
			System.out.println("2. Registrar un nuevo ejemplar ➕➕🌷🌹🌺");
			System.out.println("3. Filtrar ejemplares por tipo de planta 🌷🌹🌺🌻🌼");
			System.out.println("4. Ver mensajes de seguimiento de un ejemplar 💬💬💬💬💬");
			System.out.println("5. Añadir mensajes ➕💬➕💬➕");
			System.out.println("6. Ver mensajes 💬💬💬💬💬");
			System.out.println("7. Salir al menú principal 🔙🔙🔙🔙🔙");
			System.out.print("Seleccione una opción: ");
			option = Utilities.pedirEntero(scanner.nextLine(), scanner);
			switch (option) {
			case 1:
				System.out.println("-----------------------------------------");
				System.out.println(plantServ.listaPlantas());
				break;
			case 2:
				System.out.println("-----------------------------------------");
				registrarEjemplar(persona);
				break;
			case 3:
				System.out.println("-----------------------------------------");
				buscarEjemplaresXtipoDePlanta();
				break;
			case 4:
				System.out.println("-----------------------------------------");
				VerMensajesSeguimientoEjemplar();
				break;
			case 5:
				añadirMensajes(persona);
				break;
			case 6:
				menuMensajesPersona(persona);
				break;
			case 7:
				System.out.println("Regresando al menú principal...");
				break;
			default:
				System.out.println("⚠️ Opción inválida, inserte un valor válido ⚠️");
			}
		} while (option != 7);
	}


	//Menú para listar mensajes 
	private void menuMensajesPersona(Persona persona) {
		int option;
		do {
			System.out.println("\n---- Menu Mensajes ----");
			System.out.println("1. Ver Mensajes 💬💬💬💬💬");
			System.out.println("2. Ver mensajes por persona 👨‍🦰👩👩‍🦰👨‍🦳👨");
			System.out.println("3. Ver mensajes por rango de fechas 📅📅📅📅📅");
			System.out.println("4. Ver mensajes por tipo específico de planta 🌷🌹🌺🌻🌼");
			System.out.println("5. Salir al menú del usuario 🔙🔙🔙🔙🔙");
			System.out.print("Seleccione una opción: ");
			option = Utilities.pedirEntero(scanner.nextLine(), scanner);
			switch (option) {
			case 1:
				System.out.println("-----------------------------------------");
				System.out.println(mensajeServ.listarTodosMensajes());
				break;
			case 2:
				System.out.println("-----------------------------------------");
				mensajesXPersona();
				break;
			case 3:
				System.out.println("-----------------------------------------");
				mensajesXFechas();
				break;
			case 4:
				System.out.println("-----------------------------------------");
				mensajesXPlanta();
				break;
			case 5:
				System.out.println("Regresando al menú principal...");
				break;
			default:
				System.out.println("⚠️ Opción inválida, inserte un valor válido ⚠️");
			}
		} while (option != 5);
	}

	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//A partir de aquí están todos los submenús
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//Logueo usuarios
	private void login() {
		// Pedir al usuario que ingrese su nombre de usuario y contraseña
		System.out.print("Introduce el nombre de usuario: ");
		String usuario = scanner.nextLine();
		System.out.print("Introduce la contraseña: ");
		String password = scanner.nextLine();
		System.out.println(credencialesServ.loginUsuario(usuario, password));
		if(credencialesServ.UsuarioCorrecto(usuario, password) && usuario.equals("admin")) {
			menuAdmin(personaServ.findById(credencialesServ.obtenerCredencialesAutenticadas(usuario, password).getId()));
		}else if(credencialesServ.UsuarioCorrecto(usuario, password) && !usuario.equals("admin")){          
            menuPersonal(personaServ.findById(credencialesServ.obtenerCredencialesAutenticadas(usuario, password).getId()));
		}
	}


	private void registroUsuarios() {
	    Persona nuevoUsuario = new Persona();
	    Credenciales credencialesNUsuario = new Credenciales();

	    // Pedir datos de la persona
	    System.out.println("Introduce el nombre: ");
	    nuevoUsuario.setNombre(scanner.nextLine().trim());

	    do {
	        System.out.println("Introduce el correo del nuevo usuario: ");
	        nuevoUsuario.setEmail(scanner.nextLine().trim());
	        if (personaServ.CorreoExistente(nuevoUsuario)) {
	            System.out.println("Ese correo ya está registrado.");
	        }
	        if (!Utilities.ValidEmail(nuevoUsuario.getEmail())) {
	            System.out.println("Correo no válido.");
	        }
	    } while (!Utilities.ValidEmail(nuevoUsuario.getEmail()) || personaServ.CorreoExistente(nuevoUsuario));

	    // Pedir datos de las credenciales
	    do {
	        System.out.println("Introduce el nombre de usuario: ");
	        String usuario = scanner.nextLine().trim();
	        if (usuario.contains(" ")) {
	            System.out.println("El nombre de usuario no puede contener espacios.");
	            continue;
	        }
	        credencialesNUsuario.setUsuario(usuario);

	        if (credencialesServ.UsuarioExistente(credencialesNUsuario.getUsuario())) {
	            System.out.println("Ese nombre de usuario ya existe");
	        }
	    } while (credencialesNUsuario.getUsuario() == null || 
	             credencialesNUsuario.getUsuario().contains(" ") || 
	             credencialesServ.UsuarioExistente(credencialesNUsuario.getUsuario()));

	    do {
	        System.out.println("Introduce la contraseña: ");
	        String password = scanner.nextLine().trim();
	        if (password.contains(" ")) {
	            System.out.println("La contraseña no puede contener espacios");
	            continue;
	        }
	        credencialesNUsuario.setPassword(password);
	    } while (credencialesNUsuario.getPassword() == null || credencialesNUsuario.getPassword().contains(" "));

	    // Configurar la relación entre Persona y Credenciales
	    nuevoUsuario.setCrecenciales(credencialesNUsuario);
	    credencialesNUsuario.setPersona(nuevoUsuario);

	    // Guardar la persona y sus credenciales
	    Long nuevoUsuarioID = personaServ.GuardarPersona(nuevoUsuario);

	    System.out.println("Usuario registrado con ID: " + nuevoUsuarioID);
	}



	
	// Registro de nuevo tipo de planta
	private void insertarPlanta() {
	    Planta planta = new Planta();

	    // Solicitar el código de la planta
	    String codigoPlanta;
	    do {
	        System.out.println("Introduce el código de la planta: ");
	        codigoPlanta = scanner.nextLine().trim().toUpperCase();
	        if (codigoPlanta.isEmpty() || codigoPlanta.contains(" ")) {
	            System.out.println("El código de la planta no puede estar vacío ni contener espacios.");
	        }
	    } while (codigoPlanta.isEmpty() || codigoPlanta.contains(" "));
	    planta.setCodigo(codigoPlanta);

	    // Solicitar el nombre común de la planta
	    String nombreComun;
	    do {
	        System.out.println("Introduce el nombre común de la nueva planta: ");
	        nombreComun = scanner.nextLine().trim();
	        if (nombreComun.isEmpty()) {
	            System.out.println("El nombre común no puede estar vacío");
	        }
	    } while (nombreComun.isEmpty());
	    planta.setNombreComun(nombreComun);

	    // Solicitar el nombre científico de la planta
	    String nombreCientifico;
	    do {
	        System.out.println("Introduce el nombre científico de la nueva planta: ");
	        nombreCientifico = scanner.nextLine().trim();
	        if (nombreCientifico.isEmpty()) {
	            System.out.println("El nombre científico no puede estar vacío");
	        }
	    } while (nombreCientifico.isEmpty());
	    planta.setNombreCientifico(nombreCientifico);

	    // Insertar la planta y mostrar el resultado
	    System.out.println(plantServ.InsertarPlanta(planta));
	}



	//Insertar planta
	private void registrarEjemplar(Persona persona) {
	    Planta planta = null;
		System.out.println(plantServ.listaPlantas());
	    do {
	        System.out.println("Introduce el código de la planta: ");
	        String codigoPlanta = scanner.nextLine();

	        try {
	            planta = plantServ.BuscarPlantaXId(new Planta(codigoPlanta, null, null));
	        } catch (RuntimeException e) {
	            System.out.println(e.getMessage());
	            planta = null;
	        }
	    } while (planta == null);

	    Ejemplar ejemplar = ejemplarServ.registrarNuevoEjemplar(planta, persona);
	    
	    Mensaje mensaje = new Mensaje();
	    mensaje.setEjemplar(ejemplar);
	    mensaje.setPersona(persona);
	    mensaje.setFechahora(LocalDateTime.now());
	    mensaje.setMensaje("El usuario " + persona.getNombre() + " ha creado este ejemplar en la fecha " + mensaje.getFechahora());
	    mensajeServ.crearMensaje(persona, ejemplar, mensaje);
	    
	    System.out.println("Ejemplar registrado correctamente");
	}



	//Buscar ejemplares por tipo de planta
	private void buscarEjemplaresXtipoDePlanta() {
	    Set<Planta> plantas = new HashSet<>();
	    int cantidadPlantas;
	    
		System.out.println(plantServ.listaPlantas());

	    System.out.println("Introduce cuántas plantas vas a añadir: ");
	    cantidadPlantas = Utilities.pedirEntero(scanner.nextLine(), scanner);

	    while (cantidadPlantas > 0) {
	        Planta planta = null;

	        do {
	            System.out.println("Introduce el código de la planta: ");
	            String codigoPlanta = scanner.nextLine();

	            try {
	                planta = plantServ.BuscarPlantaXId(new Planta(codigoPlanta, null, null));
	            } catch (RuntimeException e) {
	                System.out.println(e.getMessage());
	            }
	        } while (planta == null);

	        plantas.add(planta);
	        cantidadPlantas--;
	    }

	    System.out.println("Ejemplares asociados a los tipos de planta especificados:");
	    System.out.println(ejemplarServ.listarEjemplaresPorTipoDePlanta(plantas));
	}

	//Ver mensaje de seguimiento de un ejemplar
	private void VerMensajesSeguimientoEjemplar() {
		Long id = (long) 0;
		System.out.println();
		System.out.println("Ejemplares: ");
		System.out.println(ejemplarServ.listarEjemplares());
		System.out.println("--------------------------------");
		do {
			System.out.println("Introduce la ID del ejemplar: ");
			id = Utilities.pedirLong(scanner.nextLine(), scanner);
			if (!ejemplarServ.existeEjemplar(id)) {
				System.out.println("Introduce la ID correcta: ");
			}
		}while (!ejemplarServ.existeEjemplar(id));
		System.out.println();
		System.out.println(mensajeServ.buscarMensajeXEjemplar(id));
	}


	//Método para añadir mensajes
	private void añadirMensajes(Persona persona) {
		Mensaje mensaje = new Mensaje();
		Ejemplar ejemplar = new Ejemplar();
		System.out.println();
		System.out.println("Ejemplares: ");
		System.out.println(ejemplarServ.listarEjemplares());
		System.out.println("--------------------------------");
		do {
			System.out.println("Introduce la ID del ejemplar: ");
			ejemplar.setId(Utilities.pedirLong(scanner.nextLine(), scanner));
			if (!ejemplarServ.existeEjemplar(ejemplar.getId())) {
				System.out.println("Introduce la ID correcta: ");
			}
		}while (!ejemplarServ.existeEjemplar(ejemplar.getId()));
		System.out.println("Introduce el mensaje a almacenar: ");
		mensaje.setMensaje(scanner.nextLine());
		mensaje.setFechahora(LocalDateTime.now());
		System.out.println(mensajeServ.crearMensaje(persona, ejemplar, mensaje));	
	}


	//Método para ver los mensajes de un tipo de planta
	private void mensajesXPlanta() {
	    Planta planta = null;
	    System.out.println();
		System.out.println("Plantas: ");
		System.out.println(plantServ.listaPlantas());
		System.out.println("--------------------------------");
	    do {
	        System.out.println("Introduce el código de la planta de la que quieres ver sus mensajes: ");
	        String codigoPlanta = scanner.nextLine();

	        try {
	            planta = plantServ.BuscarPlantaXId(new Planta(codigoPlanta, null, null));
	        } catch (RuntimeException e) {
	            System.out.println(e.getMessage());
	            planta = null;
	        }
	    } while (planta == null);

	    System.out.println("Mensajes relativos a la planta " + planta.getNombreComun());
	    System.out.println(mensajeServ.listarXTipoPlanta(planta));
	}


	//Método para ver los mensajes acontecidos entre unas fechas
	private void mensajesXFechas() {
		System.out.println("Introduce la primera fecha: ");
		LocalDateTime fecha1 = Utilities.pedirFechaHora(scanner.nextLine(), scanner);
		System.out.println("Introduce segunda fecha: ");
		LocalDateTime fecha2 = Utilities.pedirFechaHora(scanner.nextLine(), scanner);
		System.out.println("Mensajes relativos acontecidos entre esas fechas: ");
		if (fecha1.isBefore(fecha2)) {
			System.out.println(mensajeServ.listarXRangoFechas(fecha1, fecha2));
		}else {
			System.out.println(mensajeServ.listarXRangoFechas(fecha2, fecha1));
		}
	}


	// Ver los mensajes redactados por una persona
	private void mensajesXPersona() {
	    List<Persona> personasDisponibles = personaServ.findAll();

	    if (personasDisponibles.isEmpty()) {
	        System.out.println("No hay personas registradas en el sistema.");
	        return; // Salir del método si no hay personas
	    }

	    System.out.println("Personas disponibles:");
	    personasDisponibles.forEach(persona -> 
	        System.out.println("ID: " + persona.getId() + ", Nombre: " + persona.getNombre())
	    );

	    Persona personaSeleccionada = null;

	    do {
	        System.out.println("Introduce el ID de la persona de la que quieres ver sus mensajes: ");
	        Long idPersona = Utilities.pedirLong(scanner.nextLine(), scanner);

	        try {
	            personaSeleccionada = personaServ.findById(idPersona);
	        } catch (RuntimeException e) {
	            System.out.println(e.getMessage());
	        }

	        if (personaSeleccionada == null) {
	            System.out.println("El ID proporcionado no corresponde a ninguna persona. Intenta nuevamente.");
	        }
	    } while (personaSeleccionada == null);

	    System.out.println("Mensajes relativos a " + personaSeleccionada.getNombre() + ":");
	    System.out.println(mensajeServ.listarXPersona(personaSeleccionada));
	}


}
