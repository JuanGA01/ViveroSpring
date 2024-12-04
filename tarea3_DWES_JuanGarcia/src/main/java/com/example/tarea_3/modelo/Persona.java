package com.example.tarea_3.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Persona {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String nombre;
	
	@Column(length = 100, unique = true, nullable = false)
	private String email;
	
	@OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
	private Credenciales crecenciales;
	
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mensaje> mensajes = new ArrayList<>();
	
	public Persona() {
		super();
	}

	public Persona(Long id, String nombre, String email, Credenciales crecenciales) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.crecenciales = crecenciales;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Credenciales getCrecenciales() {
		return crecenciales;
	}

	public void setCrecenciales(Credenciales crecenciales) {
		this.crecenciales = crecenciales;
	}
	
}
