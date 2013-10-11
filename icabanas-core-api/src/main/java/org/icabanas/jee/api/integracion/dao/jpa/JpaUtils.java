package org.icabanas.jee.api.integracion.dao.jpa;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.icabanas.beans.PropertyUtils;

/**
 * Clase de utilidades para manejar JPA.
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
public final class JpaUtils {

//	public static  <T> Root<T> buscarRoot(CriteriaQuery<T> query) {
//		return buscarRoot(query, query.getResultType());
//	}
	
	/**
	 * Busca el Root de tipo clazz del CriteriaQuery.
	 * 
	 * @param criteria
	 * @param clazz
	 * @return
	 */
	public static <T> Root<T> buscarRoot(CriteriaQuery<?> criteria, Class<T> clazz){
		for (Root<?> r : criteria.getRoots()) {
			if (clazz.equals(r.getJavaType())) {
				return (Root<T>) r.as(clazz);
			}
		}
		return (Root<T>) criteria.getRoots().iterator().next();
	}
	
	/**
	 * Gets a Path from Path using property path
	 * @param path the base path
	 * @param propertyPath property path String like "customer.order.price"
	 * @return a new Path for property
	 */
	@SuppressWarnings("unchecked")
	public static <T> Path<T> getPath(Path<?> path, String propertyPath) {
		if (StringUtils.isEmpty(propertyPath))
			return (Path<T>) path;
		
		String name = StringUtils.substringBefore(propertyPath, PropertyUtils.PROPERTY_SEPARATOR);
		Path<?> p = path.get(name); 
		
		return getPath(p, StringUtils.substringAfter(propertyPath, PropertyUtils.PROPERTY_SEPARATOR));
		
	}
}
