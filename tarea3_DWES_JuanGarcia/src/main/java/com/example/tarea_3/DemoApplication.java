package com.example.tarea_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.tarea_3.facade.ViveroFacade;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
        // Inicializar el contexto de Spring
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        // Obtener el bean de ViveroFacade y mostrar el menú
        ViveroFacade portal = context.getBean(ViveroFacade.class);
        portal.mostrarMenuGestionPrincipal();
	}

}
