package org.icabanas.jee.api.integracion.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.icabanas.jee.api.integracion.dao.DaoException;
import org.icabanas.jee.api.integracion.dao.ICriteriaBuilder;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.IGenericSoloLecturaDao;
import org.icabanas.jee.api.integracion.dao.IGestorPersistencia;
import org.icabanas.jee.api.integracion.dao.IPaginador;
import org.icabanas.jee.api.integracion.dao.util.DAOUtil;

/**
 * Implementación abstracta de {@link IGenericSoloLecturaDao}.
 * 
 * <br/><br/>
 * <b>Responsabilidad</b> Implementa de forma general las operaciones de lectura sobre entidades.  
 * <br/>
 * <br/>
 *
 * @author f009994r
 *
 * @param <Id>
 * @param <Entidad>
 */
public class AbstractGenericSoloLecturaDao<Id extends Serializable, Entidad> implements
		IGenericSoloLecturaDao<Id, Entidad> {

	protected Class<Entidad> persistentClass;

	protected IGestorPersistencia gestorPersistencia;
	
	private Map<String, ICriteriaBuilder<Entidad>> criteriaBuilderMap = Collections.synchronizedMap(
			new HashMap<String, ICriteriaBuilder<Entidad>>());

//	private IProcesadorConsultas<Entidad> procesadorConsultas;
	
	public AbstractGenericSoloLecturaDao() {
		this.persistentClass = (Class<Entidad>) DAOUtil.getTypeArguments(AbstractGenericSoloLecturaDao.class, this.getClass()).get(1);
	}
	
	public AbstractGenericSoloLecturaDao(Class<Entidad> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}




	/* (non-Javadoc)
	 * @see org.icabanas.jee.api.integracion.dao.IGenericSoloLecturaDao#buscarPorId(java.lang.Object)
	 */
	public Entidad buscarPorId(Id unId) throws DaoException {
		try{														
			Entidad entidad = gestorPersistencia.buscarPorId(unId, persistentClass);				
			
			return entidad;
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.icabanas.jee.api.integracion.dao.IGenericSoloLecturaDao#buscarTodos()
	 */
	public List<Entidad> buscarTodos() throws DaoException {
		try{
			List<Entidad> elementos = gestorPersistencia.buscarTodos(persistentClass);
			
			return elementos;
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
//		finally{
//			if(getProcesadorConsultas() != null){
//				getProcesadorConsultas().nuevaConsulta();
//			}
//		}
	}

//	public ResultadoPaginado<Entidad> buscarTodos(int paginaActual,
//			int numeroRegistrosPorPagina) throws DaoException {
//		try{
//			Validate.isTrue(paginaActual >= 1, "La página actual de la paginación debe ser >= 1.");
//			Validate.isTrue(numeroRegistrosPorPagina > 0, "La página actual de la paginación debe ser > 0.");
//			
//			int numeroTotalRegistros = gestorPersistencia.contarRegistros();
//			
//			ResultadoPaginado<Entidad> resultado = null;
//			if(numeroTotalRegistros > 0){						
//				//int primerRegistro = PaginacionUtils.calculaPrimerRegistro(paginaActual,numeroRegistrosPorPagina);
//				List<Entidad> elementos = gestorPersistencia.buscarTodos(paginaActual,numeroRegistrosPorPagina);
//				
//				int numeroTotalPaginas = PaginacionUtils.calculaTotalPaginas(numeroRegistrosPorPagina, numeroTotalRegistros);
//			
//				resultado = new ResultadoPaginado<Entidad>(elementos, paginaActual, numeroTotalPaginas, numeroTotalRegistros);
//			}
//			else{
//				resultado = new ResultadoPaginado<Entidad>(new ArrayList<Entidad>(), paginaActual, 0, numeroTotalRegistros);
//			}
//			
//			return resultado;
//		}
//		catch(RuntimeException e){
//			throw new DaoException(e);
//		}
////		finally{
////			if(getProcesadorConsultas() != null){
////				getProcesadorConsultas().nuevaConsulta();
////			}
////		}
//	}

	
	public IPaginador<Entidad> paginar(IPaginador<Entidad> pagina) throws DaoException {
		try{
			Validate.isTrue(pagina != null, "El parámetro pagina no puede ser nulo.");
			Validate.isTrue(pagina.getPagina() >= 1, "La página actual de la paginación debe ser >= 1.");
			Validate.isTrue(pagina.getNumeroRegistrosPorPagina() > 0, "El número de registros por página debe ser > 0.");
			Validate.isTrue(pagina.getFiltro() != null, "El parámetro filtro no puede ser nulo.");
			
			pagina = gestorPersistencia.paginar(pagina, persistentClass, criteriaBuilderMap.get(pagina.getFiltro().getNombreFiltro()));
			
			return pagina;
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
//		finally{
//			if(getProcesadorConsultas() != null){
//				getProcesadorConsultas().nuevaConsulta();
//			}
//		}
	}
		
//	/**
//	 * @return
//	 */
//	protected IProcesadorConsultas<Entidad> getProcesadorConsultas() {
//		return this.gestorPersistencia.getProcesadorConsultas();//.nuevaConsulta();
//	}
	
//	/**
//	 * @param procesadorConsultas
//	 */
//	public void setProcesadorConsultas(
//			IProcesadorConsultas<Entidad> procesadorConsultas) {
//		this.procesadorConsultas = procesadorConsultas;
//	}

	@Override
	public List<Entidad> buscar(IFiltro filtro) throws DaoException {
		try{
			Validate.isTrue(filtro != null, "El parámetro filtro no puede ser nulo.");
			
			List<Entidad> registros = new ArrayList<Entidad>();
			
			registros = gestorPersistencia.buscar(filtro, persistentClass, criteriaBuilderMap.get(filtro.getNombreFiltro()));
			
			return registros;
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}

	@Override
	public <Entidad> Entidad buscarUnico(IFiltro filtro) throws DaoException {
		try{
			Entidad entidad = null;
			if(filtro != null){
				entidad = (Entidad) gestorPersistencia.buscarUnico(filtro, persistentClass, criteriaBuilderMap.get(filtro.getNombreFiltro()));
			}
			
			return entidad;
		}
		catch(RuntimeException e){
			throw new DaoException(e);
		}
	}


	/**
	 * Establece el gestor de persistencia del mecanismo de persistencia.
	 * 
	 * @param gestorPersistencia
	 */
	public void setGestorPersistencia(
			IGestorPersistencia gestorPersistencia) {
		this.gestorPersistencia = gestorPersistencia;
//		this.gestorPersistencia.setPersistentClass(persistentClass);
//		getProcesadorConsultas().setPersistentClass(persistentClass);
	}

	/**
	 * Devuelve el gestor de persistencia del mecanimos de persistencia.
	 * 
	 * @return
	 */
	protected IGestorPersistencia getGestorPersistencia() {
		return gestorPersistencia;
	}


	public Class<Entidad> getPersistentClass() {
		return persistentClass;
	}


	public void setPersistentClass(Class<Entidad> persistentClass) {
		this.persistentClass = persistentClass;
	}


	public Map<String, ICriteriaBuilder<Entidad>> getCriteriaBuilderMap() {
		return criteriaBuilderMap;
	}


	public void setCriteriaBuilderMap(
			Map<String, ICriteriaBuilder<Entidad>> criteriaBuilderMap) {
		this.criteriaBuilderMap = criteriaBuilderMap;
	}
	
	
}
