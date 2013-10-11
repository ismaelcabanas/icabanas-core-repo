package org.icabanas.jee.api.integracion.modelo;

import java.io.Serializable;

import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;

public interface Entidad<ID extends Serializable> {

	/**
	 * Método que devuelve el identificador de la entidad.
	 * 
	 * @return
	 */
	public ID getId();
	
	/**
	 * Método que establece el identificador de la entidad.
	 * 
	 * @param id
	 */
	public void setId(ID id);
	
	/**
	 * Método que comprueba si la entidad es vacía, es decir, que el valor de todos sus campos son nulos o 
	 * vacíos.
	 * 
	 * @return
	 */
	public boolean esVacia();
	
	/**
	 * Valida una entidad.
	 * 
	 * @return
	 * 		True si la entidad es válida.
	 * 
	 * @throws ValidacionException
	 * 		En el caso que la entidad no sea válida, indicando los campos erróneos.
	 */
	public boolean valida() throws ValidacionException;
}
