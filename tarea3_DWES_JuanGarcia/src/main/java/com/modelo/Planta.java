package com.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Planta implements Comparable<Planta> {
	
	@Id
    @Column(length = 50, unique = true, nullable = false)
	private String codigo;
	
	@Column(length = 100, nullable = false)
	private String nombreComun;
	
	@Column(length = 100, nullable = false)
	private String nombreCientifico;
	
	@OneToMany(mappedBy = "planta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ejemplar> ejemplares = new ArrayList<>();
	
	public Planta() {
		super();
	}
	
	public Planta(String codigo, String nombreComun, String nombreCientifico) {
		super();
		this.codigo = codigo;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNombreComun() {
		return nombreComun;
	}
	
	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}
	
	public String getNombreCientifico() {
		return nombreCientifico;
	}
	
	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	@Override
	public int compareTo(Planta o) {
		if (this.nombreComun.compareTo(o.nombreComun) == 0)
	      {
	         return this.nombreComun.compareTo(o.nombreComun);
	      }
	      else return this.nombreComun.compareTo(o.nombreComun);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, nombreCientifico, nombreComun);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planta other = (Planta) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(nombreCientifico, other.nombreCientifico)
				&& Objects.equals(nombreComun, other.nombreComun);
	}
	
}

