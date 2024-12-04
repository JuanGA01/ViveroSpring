package com.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Credenciales {
	
	@Id
	private Long id;
	
	@Column(length = 50, unique = true, nullable = false)
	private String usuario;
	 
	@Column(length = 100, nullable = false)
	private String password;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Persona persona;
	
	public Credenciales() {
		super();
	}

	public Credenciales(Long id, String usuario, String password) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
