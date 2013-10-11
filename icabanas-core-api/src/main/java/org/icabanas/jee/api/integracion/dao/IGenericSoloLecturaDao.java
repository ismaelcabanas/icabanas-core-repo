package org.icabanas.jee.api.integracion.dao;

import java.io.Serializable;
import java.util.List;



/**
 * Patrón Data Access Object (DAO): Nos abstrae de cualquier tipo de base de datos o mecanismo de 
 * persistencia. Proporciona operaciones sin exponer los detalles del mecanismo de persistencia.
 * <br/><br/>
 * <b>Responsabilidad</b> :  Proporciona acceso a datos de solo lectura.
 * 
 * @author f009994r
 *
 * @param <Id> Tipo de identificador
 * @param <Entidad> Tipo de Entidad
 */
public interface IGenericSoloLecturaDao<Id extends Serializable,Entidad> {

	/**
	 * Devuelve la entidad del mecanismo de persistencia utilizando el identificador proporcionado. 
	 *  
	 * @param unId 
	 * 		Identificdor de la entidad a recuperar.
	 * 
	 * @return
	 * 		Entidad para el identificador proporcionado.
	 * @throws
	 * 		DaoException
	 */
	Entidad buscarPorId(Id unId) throws DaoException;

	/**
	 * Devuelve todas las entidades de tipo <Entidad> del mecanismo de persistencia.
	 *  
	 * @return
	 * 		Instancia de {@link ResultadoPaginado}. 
	 * @throws
	 * 		DaoException
	 */
	List<Entidad> buscarTodos() throws DaoException;

//	/**
//	 * Devuelve todas las entidades de tipo <Entidad> del mecanismo de persistencia paginado.
//	 * 
//	 * @param paginaActual
//	 * 		Página actual
//	 * @param numeroRegistrosPorPagina
//	 * 		Número de reigstros por página.
//	 * @return
//	 * 		Instancia de {@link ResultadoPaginado}.
//	 * @throws
//	 * 		DaoException
//	 */
//	ResultadoPaginado<Entidad> buscarTodos(int paginaActual, int numeroRegistrosPorPagina) throws DaoException;
	
	/**
	 * Devuelve las entidades de tipo Entidad en base a un filtro de búsqueda.
	 * 
	 * @param filtro
	 * @return
	 * @throws DaoException
	 */
	List<Entidad> buscar(IFiltro filtro) throws DaoException;
	
	/**
	 * Devuelve la entidad que coincide con el filtro de la búsqueda.
	 * 
	 * @param filtro
	 * @return
	 * @throws DaoException
	 */
	<Entidad> Entidad buscarUnico(IFiltro filtro) throws DaoException;
	
	/**
	 * Pagina un conjunto de registros.
	 * 
	 * @param pagina
	 * 		La página
	 * @return
	 * @throws DaoException
	 */
	IPaginador<Entidad> paginar(IPaginador<Entidad> pagina) throws DaoException;
	
//	/**
//	 * Devuelve una nueva instancia del procesador de consultas. 
//	 * 
//	 * El procesador de consultas se encarga de realizar consultas de sólo lectura al mecanismo de persistencia, 
//	 * y por tanto, se devuelve una instancia nueva ya que cada consulta es distinta.
//	 * 
//	 * @return
//	 */
//	IProcesadorConsultas<Entidad> getProcesadorConsultas();
}
