package com.BananaTechies.models;


public class Tarea {
		private int idt;
		private String titulo;
		private Proyecto proyecto;
		private String responsable;
		private boolean status;
		private String progreso;
		private String fechaInicio;
		private String fechaFinal;
		
		public Tarea(){}
	
		
		public Tarea(int idt,String titulo,Proyecto proyecto,String responsable,boolean status,String progreso,String fechaInicio,String fechaFinal){
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

		public void setResponsable(String responsable) {
			this.responsable = responsable;
		}

		public String getResponsable() {
			 return this.responsable;
		}

		
		public boolean isStatus() {
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
		
	}


