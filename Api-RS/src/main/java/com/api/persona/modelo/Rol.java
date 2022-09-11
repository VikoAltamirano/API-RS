package com.api.persona.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "roles")
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoRol;
	
	private String descripcionRol;
	
	public Rol() {
		super();
	}
	public int getCodigoRol() {
		return codigoRol;
	}
	public void setCodigoRol(int codigoRol) {
		this.codigoRol = codigoRol;
	}
	public String getDescripcionRol() {
		return descripcionRol;
	}
	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

}
