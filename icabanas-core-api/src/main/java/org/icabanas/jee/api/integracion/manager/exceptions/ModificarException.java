package org.icabanas.jee.api.integracion.manager.exceptions;

import java.util.List;

public class ModificarException extends Exception {

	private static final long serialVersionUID = 1L;

	public ModificarException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ModificarException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ModificarException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ModificarException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public List<ErrorValidacion> getListaExcepciones() {
		if(getCause() instanceof ValidacionException){
			ValidacionException valException = (ValidacionException) getCause();
			return valException.getListaExcepciones();
		}
		return null;
	}
}
