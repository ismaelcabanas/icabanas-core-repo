package org.icabanas.jee.api.integracion.dao;

import java.io.Serializable;




/**
 * Patr�n Data Access Object (DAO): Nos abstrae de cualquier tipo de base de datos o mecanismo de 
 * persistencia. Proporciona operaciones sin exponer los detalles del mecanismo de persistencia.
 * <br/><br/>
 * <b>Responsabilidad</b> :  
 * <br/>
 * <br/>
 * <ul>
 * <li>Crear una nueva entidad en el repositorio de persistencia.</li> 
 * <li>Recuperar entidades del repositorio de persistencia.</li> 
 * <li>Actualizar una entidad del repositorio de persistencia.</li> 
 * <li>Eliminar una entidad del repositorio de persistencia.</li> 
 * <li>Eliminar todos los registros de una entidad del repositorio de persistencia.</li> 
 * </ul>
 * 
 * @author f009994r
 *
 * @param <Id> Tipo de identificador
 * @param <Entidad> Tipo de Entidad
 */
public interface IGenericDao<Id extends Serializable, Entidad> extends IGenericSoloLecturaDao<Id, Entidad> {

	/**
	 * M�todo que almacena los datos de una entidad en el repositorio de datos.
	 * 
	 * @param unaEntidad
	 * 		Instancia de tipo Entidad a persistir.
	 * 
	 * @throws
	 * 		Si se produce alguna excepci�n durante la creaci�n de la entidad.
	 */
	void crear(Entidad unaEntidad) throws DaoException;

	/**
	 * M�todo que modifica los datos de una entidad del repositorio de datos.
	 * 
	 * @param unaEntidad
	 * 		Instancia de tipo Entidad a modificar.
	 * 
	 * @return
	 * 		Isntancia de tipo Entidad modificada.
	 * 
	 * @throws DaoException
	 * 		Si se produce una excepci�n durante la modificaci�n de la entidad.
	 */
	Entidad modificar(Entidad unaEntidad) throws DaoException;

	/**
	 * M�todo que elimina una entidad del repositorio de datos.
	 * 
	 * @param unaEntidad
	 * 		Instancia de tipo Entidad a eliminar.
	 * 
	 * @throws DaoException
	 * 		Si se produce una excepci�n durante la eliminaci�n de la entidad.
	 */
	void eliminar(Entidad unaEntidad) throws DaoException;

	/**
	 * M�todo que elimina todas las entidades del repositorio de datos.
	 * 
	 * @throws DaoException
	 * 		Si se produce alguna excepci�n al eliminar las entidades.
	 */
	void eliminarTodas() throws DaoException;	
	
}
