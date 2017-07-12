package com.BananaTechies.models;

public class Mensaje {
	private String tipo;
	private String cuerpo;

	public Mensaje(int tipo, String cuerpo) {
		super();
		this.tipo = tipo==0?"<< INFORMACIO >>: ":"<< ERROR SEVERO >>: ";
		this.cuerpo = cuerpo;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo==0?"<< INFORMACIO >>: ":"<< ERROR SEVERO >>: ";
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	
}
