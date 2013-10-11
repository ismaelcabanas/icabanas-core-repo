package org.icabanas.jee.api.integracion.dao.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.util.DAOUtil;


public abstract class JPACriteriaBuilderSupport<T, F extends IFiltro> implements IJPACriteriaBuilder<T>{

	protected F filtro;
	protected Root<T> root;
	protected CriteriaBuilder cb;
	protected Class<T> entityClass;
	
	
	public JPACriteriaBuilderSupport() {
		this.entityClass = (Class<T>) DAOUtil.getTypeArguments(JPACriteriaBuilderSupport.class, this.getClass()).get(0);
	}


	public JPACriteriaBuilderSupport(Class<T> entityClass) {
		this.entityClass = entityClass;
	}


	@Override
	public CriteriaQuery<T> construir(CriteriaQuery<T> criteria, CriteriaBuilder cb,
			IFiltro filtro) {
		this.filtro = (F) filtro;
		root = criteria.from(entityClass);
		this.cb = cb;
		
		doConstruir(criteria, cb, (F) filtro);
		
		return criteria;
	}


	/**
	 * Construye el criteria
	 * 
	 * @param criteria
	 * @param cb
	 * @param filtro
	 */
	protected abstract void doConstruir(CriteriaQuery<T> criteria, CriteriaBuilder cb,
			F filtro);

	/**
	 * Añade la restricción '=' al criteria.
	 * 
	 * @param propiedad
	 * 		La propiedad
	 * @param valor
	 * 		El valor
	 * @return
	 */
	protected Predicate igualQue(String propiedad, Object valor){
		//return cb.equal(cb.lower(root.<String>get(propiedad)), ((String) valor).toLowerCase());
		return cb.equal(JpaUtils.getPath(root, propiedad), valor);
	}
	
	/**
	 * Añade la restricción 'like' al criteria.
	 * 
	 * @param propiedad
	 * @param valor
	 * @return
	 */
	protected Predicate like(String propiedad, Object valor){
		String toMatch = ((String) valor).trim();
		toMatch = toMatch.replace('*', '%');
		toMatch = "%" + toMatch + "%";
		return cb.like(JpaUtils.<String>getPath(root, propiedad), toMatch);
	}

}
