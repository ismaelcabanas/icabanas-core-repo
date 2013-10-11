package org.icabanas.jee.api.integracion.manager.exceptions;

public class ErrorValidacion {

	private String campo;
	
	private String claveMensaje;
	
	private String mensaje;
	
	public ErrorValidacion(String campo, String mensaje) {
		this.campo = campo;
		this.claveMensaje = mensaje;
	}

	public ErrorValidacion(String claveMensaje) {
		this.claveMensaje = claveMensaje;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getClaveMensaje() {
		return claveMensaje;
	}

	public void setClaveMensaje(String mensaje) {
		this.claveMensaje = mensaje;
	}
	
	

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "ErrorValidacion [campo=" + campo + ", mensaje=" + claveMensaje + "]";
	}
	
	
}
