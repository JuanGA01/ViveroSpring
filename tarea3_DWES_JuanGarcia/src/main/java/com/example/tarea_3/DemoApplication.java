package com.example.tarea_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.tarea_3.facade.ViveroFacade;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		ViveroFacade portal = ViveroFacade.getPortal();
		portal. mostrarMenuGestionPrincipal();
	}

}
