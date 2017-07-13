package com.BananaTechies.models;
import org.codehaus.jackson.annotate.JsonProperty;

public class StatusMensaje {
	private Integer status;
	private String cuerpo;
	
	
	public  StatusMensaje() {
		
	}
	public StatusMensaje(int status, String cuerpo) {
		super();
		this.status = status;
		this.cuerpo = cuerpo;
	}
	@JsonProperty(value = "status_code")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@JsonProperty(value = "message")
	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	
	
}
