package com.BananaTechies.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.BananaTechies.models.Usuario;

@Entity
@Table(name = "proyecto")
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
		this.idp = 0;
		this.titulo = "";
		this.responsable = null;
		this.status = false;
		this.progreso = "";
		this.fechaInicio = null;
		this.fechaFinal = null;
		this.descripcion = "";
		this.notas = "";
	}

	public Proyecto(int idp, String titulo, Usuario responsable, boolean status, String progreso, String fechaInicio,
			String fechaFinal, String descripcion, String notas) {
		this.idp = idp;
		this.titulo = titulo;
		this.responsable = responsable;
		this.status = status;
		this.progreso = progreso;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.descripcion = descripcion;
		this.notas = notas;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idp")
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "responsable")
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
	
	@Temporal(TemporalType.DATE)
    @Column(name = "fechaInicio")
	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	@Temporal(TemporalType.DATE)
    @Column(name = "fechaFinal")
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