package org.icabanas.jee.api.integracion.dao.impl;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.icabanas.jee.api.integracion.dao.DaoException;
import org.icabanas.jee.api.integracion.dao.IGenericDao;

/**
 * Interface que implementa {@link IGenericDao}.
 * 
 * <br/><br/>
 * <b>Responsabilidad</b> Implementa de forma general las operacions CRUD sobre entidades  
 * <br/>
 * <br/>
 *
 * @author f009994r
 *
 * @param <Id>
 * @param <Entidad>
 */
public class GenericDao<Id extends Serializable, Entidad> extends
			GenericSoloLecturaDao<Id, Entidad> implements IGenericDao<Id, Entidad> {

	
	public GenericDao() {
		super();
	}
	
	

//	public AbstractGenericDao(
//			IGestorPersistencia<Id, Entidad> gestorPersistencia) {
//		super(gestorPersistencia);
//	}

	public GenericDao(Class<Entidad> persistentClass) {
		super(persistentClass);
	}



	/* (non-Javadoc)
	 * @see org.icabanas.jee.api.integracion.dao.IGenericDao#crear(java.lang.Object)
	 */
	public void crear(Entidad unaEntidad) throws DaoException {
		validaGestorPersistencia();
		try{
			getGestorPersistencia().crear(unaEntidad);
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.icabanas.jee.api.integracion.dao.IGenericDao#modificar(java.lang.Object)
	 */
	public Entidad modificar(Entidad unaEntidad) throws DaoException {
		validaGestorPersistencia();
		try{			
			return getGestorPersistencia().modificar(unaEntidad);
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.icabanas.jee.api.integracion.dao.IGenericDao#eliminar(java.lang.Object)
	 */
	public void eliminar(Entidad unaEntidad) throws DaoException {
		validaGestorPersistencia();
		try{			
			getGestorPersistencia().eliminar(unaEntidad);
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.icabanas.jee.api.integracion.dao.IGenericDao#eliminarTodas()
	 */
	public void eliminarTodas() throws DaoException {
		validaGestorPersistencia();
		try{
			getGestorPersistencia().eliminarTodas();
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}	
	
}
