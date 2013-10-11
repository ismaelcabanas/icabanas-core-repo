package org.icabanas.jee.api.integracion.dao.impl;

import java.io.Serializable;

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
public class AbstractGenericDao<Id extends Serializable, Entidad> extends
			AbstractGenericSoloLecturaDao<Id, Entidad> implements IGenericDao<Id, Entidad> {

	
	public AbstractGenericDao() {
		super();
	}
	
	

//	public AbstractGenericDao(
//			IGestorPersistencia<Id, Entidad> gestorPersistencia) {
//		super(gestorPersistencia);
//	}

	public AbstractGenericDao(Class<Entidad> persistentClass) {
		super(persistentClass);
	}



	/* (non-Javadoc)
	 * @see org.icabanas.jee.api.integracion.dao.IGenericDao#crear(java.lang.Object)
	 */
	public void crear(Entidad unaEntidad) throws DaoException {
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
		try{
			getGestorPersistencia().eliminarTodas();
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}

	
}
