package com.BananaTechies.models;
import com.BananaTechies.models.Progreso;
public class Proyecto {
	private int idp;
	private String titulo;
	private Usuario responsable;
	private boolean status;
	private Progreso progreso;
	private java.util.Date fechaInicio;
	private java.util.Date fechaFinal;
	private Tarea tarea;
	private String descripcion;
	private String notas;
	
	

	
	public Proyecto(int idp, String titulo, Usuario responsable , boolean status,Progreso progreso, java.util.Date fechaInicio,
			java.util.Date fechaFinal, Tarea tarea,String descripcion,String notas) {
		this.idp=idp;
		this.titulo=titulo;
		this.responsable=responsable;
		this.status=status;
		this.progreso=progreso;
		this.fechaInicio=fechaInicio;
		this.fechaFinal=fechaFinal;
		this.tarea=tarea;
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
		return titulo;
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




	public boolean isStatus() {
		return status;
	}




	public void setStatus(boolean status) {
		this.status = status;
	}




	public Progreso getProgreso() {
		return progreso;
	}




	public void setProgreso(Progreso progreso) {
		this.progreso = progreso;
	}




	public java.util.Date getFechaInicio() {
		return fechaInicio;
	}




	public void setFechaInicio(java.util.Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}




	public java.util.Date getFechaFinal() {
		return fechaFinal;
	}




	public void setFechaFinal(java.util.Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}




	public Tarea getTarea() {
		return tarea;
	}




	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
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