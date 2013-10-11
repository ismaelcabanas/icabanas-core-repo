package org.icabanas.jee.api.integracion.dao.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.icabanas.jee.api.integracion.dao.ICriteriaBuilder;
import org.icabanas.jee.api.integracion.dao.IFiltro;

/**
 * Interfaz que declara el/los método/s para construir los criterios de una consulta en 
 * base a un filtro.
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
 * @param <T>
 * 		Tipo de la clase sobre la que se hace la consulta. 
 */
public interface IJPACriteriaBuilder<T> extends ICriteriaBuilder<T> {

	/**
	 * Añade restricciones al criteria a partir del filtro.
	 * 
	 * @param criteria
	 * @param cb
	 * @param filtro
	 */
	CriteriaQuery<T> construir(CriteriaQuery<T> criteria, CriteriaBuilder cb, IFiltro filtro);
}
