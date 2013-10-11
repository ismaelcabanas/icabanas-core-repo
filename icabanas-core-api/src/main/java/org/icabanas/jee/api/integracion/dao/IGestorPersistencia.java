package org.icabanas.jee.api.integracion.dao;

import java.io.Serializable;
import java.util.List;


/**
 * Gestor de persistencia. Abstrae la implementación del mecanismo de persistencia.
 * 
 * <br/><br/>
 * <b>Responsabilidad</b> : Operaciones de acceso al mecanismo de persistencia.   
 * <br/>
 * <br/>
 * <ul>
 * <li>Operaciones CRUD.</li> 
 * <li>Operaciones de consulta.</li>
 * </ul>
 *
 * @author f009994r
 *
 */
public interface IGestorPersistencia {

	/**
	 * Crea una entidad en el mecanismo de persistencia.
	 * 
	 * @param unaEntidad
	 * 		La entidad.
	 */
	<Entidad> void crear(Entidad unaEntidad);

	/**
	 * Modifica una entidad del mecanismo de persistencia.
	 * 
	 * @param unaEntidad
	 * 		Entidad a modificar.
	 * 
	 * @return
	 * 		Entidad modificada.
	 */
	<Entidad> Entidad modificar(Entidad unaEntidad);
	
	/**
	 * Elmina una entidad del mecanismo de persistencia.
	 * 
	 * @param unaEntidad
	 * 		Entidad a eliminar.
	 */
	<Entidad> void eliminar(Entidad unaEntidad);
	
	/**
	 * Elimina todas las entidades de un tipo del mecanismo de persistencia. 
	 */
	void eliminarTodas();
	
	/**
	 * Devuelve la entidad del mecanismo de persistencia utilizando el identificador proporcionado. 
	 *  
	 * @param unId 
	 * 		Identificdor de la entidad a recuperar.
	 * @param persistentClass
	 * 		La clase persistente.
	 * @return
	 * 		Entidad para el identificador proporcionado.
	 */
	<Id extends Serializable, Entidad> Entidad buscarPorId(Id id, Class<Entidad> persistentClass);

	/**
	 * Devuelve todas las entidades de tipo Entidad del mecanismo de persistencia.
	 *  
	 *  @param persistentClass
	 * 		La clase persistente.
	 * @return
	 * 		Lista de entidades de tipo Entidad.
	 */
	<Entidad> List<Entidad> buscarTodos(Class<Entidad> persistenClass);

//	/**
//	 * Devuelve todas las entidades de tipo <T> del mecanismo de persistencia paginado.
//	 * 
//	 * @param paginaActual
//	 * 		Página actual
//	 * @param numeroRegistrosPorPagina
//	 * 		Número de reigstros por página.
//	 * @return
//	 * 
//	 */
//	List<Entidad> buscarTodos(int paginaActual, int numeroRegistrosPorPagina);
//
//	/**
//	 * Devuelve el número de registros de una entidad del mecanismo de persistencia.
//	 * 
//	 * @return
//	 */
//	int contarRegistros();
	
	/**
	 * Pagina un conjunto de resultados.
	 * 
	 * @param pagina
	 * 		Los datos de la página.
	 * @param persistentClass
	 * 		La clase persistente. 
	 * @param icb
	 * 		La instancia del la clase que construye la consulta
	 * @return
	 */
	<Entidad> IPaginador<Entidad> paginar(IPaginador<Entidad> pagina, Class<Entidad> persistentClass, ICriteriaBuilder<Entidad> icb);

	/**
	 * Realiza una consulta contra una entidad persistente aplicando un determinado filtro.
	 *   
	 * @param filtro
	 * 		El filtro.
	 * @param persistentClass
	 * 		La clase persistente.
	 * @param icb
	 * 		La instancia del la clase que construye la consulta
	 * @return
	 */
	<Entidad> List<Entidad> buscar(IFiltro filtro, Class<Entidad> persistentClass, ICriteriaBuilder<Entidad> icb);
	
	/**
	 * 
	 * @param filtro
	 * @param persistentClass
	 * @param icb
	 * @return
	 */
	<Entidad> Entidad buscarUnico(IFiltro filtro, Class<Entidad> persistentClass, ICriteriaBuilder<Entidad> icb);
	
//	/**
//	 * Devuelve el número de registros de una entidad del mecanismo de persistencia para una consulta paginada.
//	 * 
//	 * @return
//	 */
//	int contarRegistrosPaginacion();
	
//	/**
//	 * Devuelve el procesador de consultas del gestor de persistencia.
//	 * 
//	 * @return
//	 */
//	IProcesadorConsultas<Entidad> getProcesadorConsultas();
//
//	/**
//	 * Establece el procesador de consultas del gestor de persistencia.
//	 * 
//	 * @param procesadorConsultas
//	 */
//	void setProcesadorConsultas(IProcesadorConsultas<Entidad> procesadorConsultas);	

//	/**
//	 * Establece la clase persistente.
//	 * 
//	 * @param persistentClass
//	 */
//	<Entidad> void setPersistentClass(Class<Entidad> persistentClass);
	
}
