package org.icabanas.jee.api.integracion.manager.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidacionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private List<ErrorValidacion> listaExcepciones = new ArrayList<ErrorValidacion>();
	
	public ValidacionException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Método que añade un error a la lista de excepciones.
	 * @param campo Campo que produce el error.
	 * @param mensaje Mensaje de error que se proporciona al usuario.
	 */
	public void anade(String campo, String mensaje) {
		listaExcepciones.add(new ErrorValidacion(campo,mensaje));		
	}

	public void anade(String mensaje) {
		listaExcepciones.add(new ErrorValidacion(mensaje));
	}
	
	/**
	 * Método que indica si se ha producido excepciones.
	 * 
	 * @return
	 */
	public boolean hayErrores() {
		return (listaExcepciones != null ? listaExcepciones.size() > 0 : false);
	}

	public List<ErrorValidacion> getListaExcepciones() {
		return listaExcepciones;
	}

	public void setListaExcepciones(List<ErrorValidacion> listaExcepciones) {
		this.listaExcepciones = listaExcepciones;
	}
}
