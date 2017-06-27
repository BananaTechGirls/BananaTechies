package com.BananaTechies.models;



import com.BananaTechies.models.Usuario;


public class Proyecto {
	private int idp;
	private String titulo;
	private Usuario responsable;
	private boolean status;
	private String progreso;
	private String fechaInicio;
	private String fechaFinal;
	private String descripcion;
	private String notas;
	
	

	public Proyecto() {
		this.idp=0;
		this.titulo="";
		this.responsable=null;
		this.status=false;
		this.progreso="";
		this.fechaInicio=null;
		this.fechaFinal=null;
		this.descripcion="";
		this.notas="";
	}

	public Proyecto(int idp, String titulo, Usuario responsable , boolean status, String progreso, String fechaInicio, String fechaFinal, String descripcion,String notas) {
		this.idp=idp;
		this.titulo=titulo;
		this.responsable=responsable;
		this.status=status;
		this.progreso=progreso;
		this.fechaInicio=fechaInicio;
		this.fechaFinal=fechaFinal;
		this.descripcion=descripcion;
		this.notas=notas;
	}




	public int getIdp() {
		return idp;
	}




	public void setIdp(int idp) {
		this.idp = idp;
	}




	public String getTitulo() {
		return this.titulo;
	}




	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}




	public Usuario getResponsable() {
		return responsable;
	}




	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}




	public boolean getStatus() {
		return status;
	}




	public void setStatus(boolean status) {
		this.status = status;
	}




	public String getProgreso() {
		return progreso;
	}




	public void setProgreso(String progreso) {
		this.progreso = progreso;
	}




	public String getFechaInicio() {
		return fechaInicio;
	}




	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}




	public String getFechaFinal() {
		return fechaFinal;
	}




	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}



	public String getDescripcion() {
		return descripcion;
	}




	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}




	public String getNotas() {
		return notas;
	}




	public void setNotas(String notas) {
		this.notas = notas;
	}




	

}	