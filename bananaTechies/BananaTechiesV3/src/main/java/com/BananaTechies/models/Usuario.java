package com.BananaTechies.models;

//import java.util.List;

public class Usuario {
	private	int	uid;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	


	// CONSTRUCTOR SIN PARAMETROS
	public Usuario(){}
	
	// CONSTRUCTOR CON TODOS PARAMETROS (no password)
	public Usuario(int uid, String nombre, String apellido, String email,String password) {
		this.uid=uid;
		this.nombre=nombre;
		this.apellido=apellido;
		this.email=email;
		this.password = password;
		
	}

	// CONSTRUCTOR CON PARAMETROS (creado por el cliente)
	public Usuario(int uid, String nombre, String apellido) {
		this.uid=uid;
		this.nombre=nombre;
		this.apellido=apellido;		
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}