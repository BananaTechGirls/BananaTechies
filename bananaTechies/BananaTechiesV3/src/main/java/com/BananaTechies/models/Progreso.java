package com.BananaTechies.models;

public class Progreso {
	private int idpro;
	private String status;
	
	public Progreso(int idpro,String status){
		this.idpro=idpro;
		this.status=status;
		
	}

	public int getIdpro() {
		return idpro;
	}

	public void setIdpro(int idpro) {
		this.idpro = idpro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
