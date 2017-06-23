package com.BananaTechies.models;

public class Tarea {
		private int idt;
		private String titulo;
		private Proyecto proyecto;
		private Usuario responsable;
		private boolean status;
		private Progreso progreso;
		private java.util.Date fechaInicio;
		private java.util.Date fechaFinal;
		
		
		public Tarea(int idt,String titulo,Proyecto proyecto,Usuario responsable,boolean status,Progreso progreso,java.util.Date fechaInicio,java.util.Date fechaFinal){
			this.idt=idt;
			this.titulo=titulo;
			this.proyecto=proyecto;
			this.responsable=responsable;
			this.status=status;
			this.progreso=progreso;
			this.fechaInicio=fechaInicio;
			this.fechaFinal=fechaFinal;
		}


		public int getIdt() {
			return idt;
		}


		public void setIdt(int idt) {
			this.idt = idt;
		}


		public String getTitulo() {
			return titulo;
		}


		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}


		public Proyecto getProyecto() {
			return proyecto;
		}


		public void setProyecto(Proyecto proyecto) {
			this.proyecto = proyecto;
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
		
	}


