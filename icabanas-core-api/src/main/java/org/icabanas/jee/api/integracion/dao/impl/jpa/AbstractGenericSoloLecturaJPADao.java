package org.icabanas.jee.api.integracion.dao.impl.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.Validate;
import org.icabanas.jee.api.integracion.dao.DaoException;
import org.icabanas.jee.api.integracion.dao.ICriteriaBuilder;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.IGenericSoloLecturaDao;
import org.icabanas.jee.api.integracion.dao.IPaginador;
import org.icabanas.jee.api.integracion.dao.jpa.IJPACriteriaBuilder;
import org.icabanas.jee.api.integracion.dao.jpa.JpaUtils;
import org.icabanas.jee.api.integracion.dao.util.DAOUtil;
import org.springframework.stereotype.Repository;

/**
 * Implementación del mecanismo de persistencia de consultas basado en JPA.
 * 
 * <br/><br/>
 * <b>Responsabilidad</b> :  Implementa las operaciones de lectura del repositorio de almacenamiento.
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
public abstract class AbstractGenericSoloLecturaJPADao<Id extends Serializable, Entidad> implements
		IGenericSoloLecturaDao<Id, Entidad> {

	/**
	 * EntityManager
	 */
	@PersistenceContext
	protected EntityManager entityManager;
	
	/**
	 * Clase persistente
	 */
	private Class<Entidad> persistentClass;
	
	
	public AbstractGenericSoloLecturaJPADao() {
		this.persistentClass = (Class<Entidad>) DAOUtil.getTypeArguments(AbstractGenericSoloLecturaJPADao.class, this.getClass()).get(1);
	}

	@Override
	public Entidad buscarPorId(Id unId) {
		try{														
			Validate.notNull(unId, "El parámetro unId no puede ser nulo.");
			try{
				return entityManager.find(persistentClass, unId);
			}
			catch(EntityNotFoundException e){}
			
			return null;
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}

	@Override
	public List<Entidad> buscarTodos() {
		try{
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Entidad> criteria = cb.createQuery(persistentClass);
			criteria.select(criteria.from(persistentClass));
			
			TypedQuery<Entidad> query = entityManager.createQuery(criteria);
			
			return query.getResultList();
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}

	@Override
	public List<Entidad> buscar(IFiltro filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <Entidad> Entidad buscarUnico(IFiltro filtro)  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPaginador<Entidad> paginar(IPaginador<Entidad> pagina){
		// TODO Auto-generated method stub
		return null;
	}

	private <Entidad> CriteriaQuery<Long> getCriteriaCount(IFiltro filtro, final Class<Entidad> persistentClass, final ICriteriaBuilder<Entidad> icb){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaCount = (CriteriaQuery<Long>) getCriteria(filtro, persistentClass, icb);
		Root<Entidad> countRoot = JpaUtils.buscarRoot(criteriaCount,persistentClass);
		criteriaCount.select(cb.count(countRoot));
		
		return criteriaCount;
	}
	
	
	private <Entidad> CriteriaQuery<Entidad> getCriteria(IFiltro filtro, final Class<Entidad> persistentClass, final ICriteriaBuilder<Entidad> icb){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Entidad> criteria = cb.createQuery(persistentClass);
		
		if(filtro != null && icb != null){
			IJPACriteriaBuilder<Entidad> jpaCb = (IJPACriteriaBuilder<Entidad>) icb;
			jpaCb.construir(criteria,cb,filtro);
		}
		else{ // Si el filtro es vació devuelvo el criteria que selecciona todos los registros de la entidad
			criteria.select(criteria.from(persistentClass));			
		}
		
		return criteria;
	}
	
	private <Entidad> TypedQuery<Entidad> getQuery(IPaginador<Entidad> pagina, final Class<Entidad> persistentClass, final ICriteriaBuilder<Entidad> icb){
		// criteria para la consulta paginada
		CriteriaQuery<Entidad> criteria = getCriteria(pagina.getFiltro(), persistentClass, icb);
		
		// criteria para contar los registros de la consulta
		CriteriaQuery<Long> criteriaCount = getCriteriaCount(pagina.getFiltro(), persistentClass, icb);
		
		// ejecución de la consulta que cuenta los registros de la consulta y establece el número total de registros en la pagina
		int numeroTotalRegistros = entityManager.createQuery(criteriaCount).getSingleResult().intValue();
		pagina.setNumeroTotalRegistros(numeroTotalRegistros);
		
		return entityManager.createQuery(criteria);
	}
	
	private <Entidad> TypedQuery<Entidad> getQuery(IFiltro filtro, final Class<Entidad> persistentClass, final ICriteriaBuilder<Entidad> icb){
		// criteria para la consulta paginada
		CriteriaQuery<Entidad> criteria = getCriteria(filtro, persistentClass, icb);
			
		return entityManager.createQuery(criteria);
	}

}
