package org.icabanas.jee.api.integracion.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.Validate;
import org.icabanas.jee.api.integracion.dao.ICriteriaBuilder;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.IGestorPersistencia;
import org.icabanas.jee.api.integracion.dao.IPaginador;

public class GestorPersistenciaJPA implements IGestorPersistencia {

	@PersistenceContext
	private EntityManager entityManager;
	
	public GestorPersistenciaJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public GestorPersistenciaJPA() {}

	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public <Entidad> void crear(Entidad unaEntidad) {
		Validate.isTrue(unaEntidad != null, "El parámetro unaEntidad no debe ser nulo.");
		entityManager.persist(unaEntidad);
	}

	@Override
	public <Entidad> Entidad modificar(Entidad unaEntidad) {
		Validate.isTrue(unaEntidad != null, "El parámetro unaEntidad no debe ser nulo.");
		return entityManager.merge(unaEntidad);
	}

	@Override
	public <Entidad> void eliminar(Entidad unaEntidad) {
		Validate.isTrue(unaEntidad != null, "El parámetro unaEntidad no debe ser nulo.");
		entityManager.remove(unaEntidad);
		
	}

	@Override
	public void eliminarTodas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <Id extends Serializable, Entidad> Entidad buscarPorId(Id id,
			Class<Entidad> persistentClass) {
		Validate.notNull(id, "El parámetro unId no puede ser nulo.");
		try{
			return entityManager.find(persistentClass, id);
		}
		catch(EntityNotFoundException e){}
		
		return null;
	}

	@Override
	public <Entidad> List<Entidad> buscarTodos(Class<Entidad> persistentClass) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Entidad> criteria = cb.createQuery(persistentClass);
		criteria.select(criteria.from(persistentClass));
		
		TypedQuery<Entidad> query = entityManager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	@Override
	public <Entidad> List<Entidad> buscar(IFiltro filtro,
			Class<Entidad> persistentClass, ICriteriaBuilder<Entidad> icb) {
		// query para obtener la consulta paginada
		TypedQuery<Entidad> query = getQuery(filtro, persistentClass, icb);

		// realizo la consulta
		List<Entidad> registros = query.getResultList();
		
		return registros;
	}		

	@Override
	public <Entidad> Entidad buscarUnico(IFiltro filtro,
			Class<Entidad> persistentClass, ICriteriaBuilder<Entidad> icb) {
		Entidad entidad;
		TypedQuery<Entidad> query = getQuery(filtro, persistentClass, icb);
		
		try{
			entidad = query.getSingleResult();
		}
		catch(NoResultException e){
			entidad = null;
		}
		
		return entidad;
	}

	@Override
	public <Entidad> IPaginador<Entidad> paginar(IPaginador<Entidad> pagina, final Class<Entidad> persistentClass, ICriteriaBuilder<Entidad> icb) {						
		// query para obtener la consulta paginada
		TypedQuery<Entidad> query = getQuery(pagina, persistentClass, icb);
		
		// añado a la query la información necesaria para la paginación
		query.setFirstResult(pagina.getPrimerRegistro());
		query.setMaxResults(pagina.getNumeroRegistrosPorPagina());
		
		// realizo la consulta
		List<Entidad> registros = query.getResultList();
		
		pagina.setDatos(registros);		
				
		return pagina;
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
