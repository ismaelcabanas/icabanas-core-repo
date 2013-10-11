package org.icabanas.jee.api.integracion.dao;

/**
 * Clase que encapsula las excepciones producidas en la capa de acceso a datos.
 * 
 * @author f009994r
 *
 */
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}	

}
