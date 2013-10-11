package org.icabanas.jee.api.integracion.dao;

import java.util.List;

/**
 * Clase que representa un filtro de una consulta sobre una entidad.
 * 
 * <br/><br/>
 * <b>Responsabilidad</b> :  
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
public interface IFiltro {

	/**
	 * Establece un orden para el filtro.
	 * 
	 * @param orden
	 */
	void setOrden(List<Orden> orden);

	/**
	 * Nombre del filtro.
	 * 
	 * @return
	 */
	String getNombreFiltro();

	/**
	 * @param nombreFiltro
	 */
	void setNombreFiltro(String nombreFiltro);
	
	/**
	 * Limpia el filtro de búsqueda.
	 */
	void limpiar();
		
	/**
	 * @return 
	 * 		true, si el filtro de búsqueda es vacío.
	 * 		false, en caso contrario.
	 */
	boolean esVacio();

}
