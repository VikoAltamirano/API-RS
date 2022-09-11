package com.api.persona.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "usuario")
public class Persona {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

private String nombre;
private String correo;
private String celular;
private String contrasena;
private int rol;

public Persona() {
	super();
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getCorreo() {
	return correo;
}

public void setCorreo(String correo) {
	this.correo = correo;
}

public String getCelular() {
	return celular;
}

public void setCelular(String celular) {
	this.celular = celular;
}

public String getContrasena() {
	return contrasena;
}

public void setContrasena(String contrasena) {
	this.contrasena = contrasena;
}

public int getRol() {
	return rol;
}

public void setRol(int rol) {
	this.rol = rol;
}


}
