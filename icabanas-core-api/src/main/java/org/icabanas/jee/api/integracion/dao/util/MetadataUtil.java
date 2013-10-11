package org.icabanas.jee.api.integracion.dao.util;

import java.io.Serializable;

/**
 * El framework utiliza una implementación de esta interfaz para hacer 
 * instrospección de los objetos y relaciones mantenidas por el proveedor JPA.
 * 
 * Esta interfaz proporciona una capa de abstracción entre el framework y 
 * el proveedor ORM o JPA (por ejemplo Hibernate). Cambiando la implementación de 
 * esta interfaz, el framework debería ser capaz de ser utilizado por distintos proveedores JPA.
 * 
 * @author f009994r
 *
 */
public interface MetadataUtil {

	/**
	 * Método que obtiene el identificador de un objeto.
	 * 
	 * @param objeto
	 * @return
	 */
	Serializable getId(Object objeto);
	
	/**
	 * Método que obtiene el Metadata de una clase de entidad
	 * 
	 * @throws IllegalArgumentException
	 *             si la clase no es una entidad.
	 */
	public Metadata get(Class<?> klass);
}
