package com.example.tarea_3.facade;

import java.time.LocalDateTime;
import java.util.HashSet;
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
	private void menuAdmin() {
		int option;
		do {
			System.out.println("\n---- Admin Menu ----");
			System.out.println("1. Ver Plantas 🌷🌹🌺🌻🌼");
			System.out.println("2. Gestionar usuarios");
			System.out.println("3. Insertar/modificar planta");
			System.out.println("4. Salir al menú principal");
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
				insertarPlanta();
				break;
			case 4:
				System.out.println("Regresando al menú principal...");
				break;
			default:
				System.out.println("Opción inválida. Intente de nuevo.");
			}
		} while (option != 4);
	}


	//Menú para los no administradores
	private void menuPersonal(Persona persona) {
		int option;
		do {
			System.out.println("\n---- Menu Personal ----");
			System.out.println("1. Ver Plantas");
			System.out.println("2. Registrar un nuevo ejemplar");
			System.out.println("3. Filtrar ejemplares por tipo de planta");
			System.out.println("4. Ver mensajes de seguimiento de un ejemplar");
			System.out.println("5. Añadir mensajes");
			System.out.println("6. Ver mensajes");
			System.out.println("7. Salir");
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
				System.out.println("Opción inválida. Intente de nuevo.");
			}
		} while (option != 7);
	}


	//Menú para listar mensajes (NO ADMINISTRADORES)
	private void menuMensajesPersona(Persona persona) {
		int option;
		do {
			System.out.println("\n---- Menu Mensajes ----");
			System.out.println("1. Ver Mensajes");
			System.out.println("2. Ver mensajes por persona");
			System.out.println("3. Ver mensajes por rango de fechas");
			System.out.println("4. Ver mensajes por tipo específico de planta");
			System.out.println("5. Salir");
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
				System.out.println("Opción inválida. Intente de nuevo.");
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
			menuAdmin();
		}else if(credencialesServ.UsuarioCorrecto(usuario, password) && !usuario.equals("admin")){          
            menuPersonal(personaServ.findById(credencialesServ.obtenerCredencialesAutenticadas(usuario, password).getId()));
		}
	}


	private void registroUsuarios() {
	    Persona nuevoUsuario = new Persona();
	    Credenciales credencialesNUsuario = new Credenciales();

	    // Pedir datos de la persona
	    System.out.println("Introduce el nombre: ");
	    nuevoUsuario.setNombre(scanner.nextLine());

	    do {
	        System.out.println("Introduce el correo del nuevo usuario: ");
	        nuevoUsuario.setEmail(scanner.nextLine());
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
	        credencialesNUsuario.setUsuario(scanner.nextLine());
	        if (credencialesServ.UsuarioExistente(credencialesNUsuario.getUsuario())) {
	            System.out.println("Ese nombre de usuario ya existe.");
	        }
	    } while (credencialesServ.UsuarioExistente(credencialesNUsuario.getUsuario()));

	    System.out.println("Introduce la contraseña: ");
	    credencialesNUsuario.setPassword(scanner.nextLine());

	    // Configurar la relación entre Persona y Credenciales
	    nuevoUsuario.setCrecenciales(credencialesNUsuario);
	    credencialesNUsuario.setPersona(nuevoUsuario);

	    // Guardar la persona y sus credenciales
	    Long nuevoUsuarioID = personaServ.GuardarPersona(nuevoUsuario);

	    System.out.println("Usuario registrado con ID: " + nuevoUsuarioID);
	}


	
	//Registro de nuevo tipo de planta
	private void insertarPlanta() {
		Planta planta = new Planta();
		System.out.println("Introduce el código de la planta: ");
		planta.setCodigo(scanner.nextLine());
		System.out.println("Introduce el nombre común de la nueva planta: ");
		planta.setNombreComun(scanner.nextLine());
		System.out.println("Introduce el nombre científico de la nueva planta: ");
		planta.setNombreCientifico(scanner.nextLine());
		System.out.println(plantServ.InsertarPlanta(planta));
	}


	//Insertar planta
	private void registrarEjemplar(Persona persona) {
	    Planta planta = null;

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

	    ejemplarServ.registrarNuevoEjemplar(planta, persona);
	}



	//Buscar ejemplares por tipo de planta
	private void buscarEjemplaresXtipoDePlanta() {
	    Set<Planta> plantas = new HashSet<>();
	    int cantidadPlantas;

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


	//Ver los mensajes redactados por una persona
	private void mensajesXPersona() {
	    Persona persona = null;

	    do {
	        System.out.println("Introduce el código de la persona de la que quieres ver sus mensajes: ");
	        Long idPersona = Utilities.pedirLong(scanner.nextLine(), scanner);

	        try {
	            persona = personaServ.findById(idPersona);
	        } catch (RuntimeException e) {
	            System.out.println(e.getMessage());
	        }
	    } while (persona == null);

	    System.out.println("Mensajes relativos a " + persona.getNombre());
	    System.out.println(mensajeServ.listarXPersona(persona));
	}



}
