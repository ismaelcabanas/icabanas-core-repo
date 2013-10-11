package org.icabanas.jee.api.integracion.dao;

import java.io.Serializable;

/**
 * 
 * <br/><br/>
 * <b>Responsabilidad</b>: Factoría de Daos.   
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
public interface IDaoFactory {

	/**
	 * Crea un Dao genérico para una entidad.
	 * 
	 * @param persistentClass
	 * 		La entidad persistente.
	 * @return
	 */
	<Id extends Serializable,Entidad> IGenericDao<Id, Entidad> crearDao(Class<Entidad> persistentClass);
	
	/**
	 * Crea un Dao genérico de solo lectura para una entidad.
	 * 
	 * @param persistentClass
	 * 		La entidad persistente.
	 * @return
	 */
	<Id extends Serializable,Entidad> IGenericSoloLecturaDao<Id, Entidad> crearDaoSoloLectura(Class<Entidad> persistentClass);
}
