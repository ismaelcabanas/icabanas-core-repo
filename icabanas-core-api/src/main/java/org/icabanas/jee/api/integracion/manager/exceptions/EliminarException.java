package org.icabanas.jee.api.integracion.manager.exceptions;

import java.util.List;


public class EliminarException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public EliminarException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EliminarException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public EliminarException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public EliminarException(Throwable arg0) {
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
