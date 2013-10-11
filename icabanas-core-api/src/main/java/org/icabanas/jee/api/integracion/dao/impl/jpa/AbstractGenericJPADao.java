package org.icabanas.jee.api.integracion.dao.impl.jpa;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.icabanas.jee.api.integracion.dao.DaoException;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.springframework.stereotype.Repository;

/**
 * Implementación del mecanismo de persistencia basado en JPA.
 * 
 * <br/><br/>
 * <b>Responsabilidad</b> : Implementa las operaciones de persistencia del repositorio de almacenamiento.  
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 * @param <Id>
 * @param <Entidad>
 */
@Repository
public abstract class AbstractGenericJPADao<Id extends Serializable, Entidad> extends
		AbstractGenericSoloLecturaJPADao<Id, Entidad> implements IGenericDao<Id, Entidad> {

	@Override
	public void crear(Entidad unaEntidad) {
		try{
			Validate.isTrue(unaEntidad != null, "El parámetro unaEntidad no debe ser nulo.");
			entityManager.persist(unaEntidad);
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}

	@Override
	public Entidad modificar(Entidad unaEntidad) {
		try{
			Validate.isTrue(unaEntidad != null, "El parámetro unaEntidad no debe ser nulo.");
			return entityManager.merge(unaEntidad);
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}

	@Override
	public void eliminar(Entidad unaEntidad) {
		try{
			Validate.isTrue(unaEntidad != null, "El parámetro unaEntidad no debe ser nulo.");
			entityManager.remove(unaEntidad);
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}

	@Override
	public void eliminarTodas() {
		throw new UnsupportedOperationException("No implementado aún.");
	}

	
}
