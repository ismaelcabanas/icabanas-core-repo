package org.icabanas.jee.api.integracion.dao;

import java.util.List;

/**
 * Interface que representa una paginador de registros.
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
 * @param <T>
 * 		Tipo de datos del paginador.
 */
public interface IPaginador<T> {

	/**
	 * @return
	 * 		La página actual.
	 */
	int getPagina();

	/**
	 * @return
	 * 		Tamaño de página.
	 */
	int getNumeroRegistrosPorPagina();

	/**
	 * @return
	 * 		Número total de páginas.
	 */
	int getNumeroTotalPaginas();

	/**
	 * @return
	 * 		Número total de registros.
	 */
	int getNumeroTotalRegistros();

	/**
	 * @return
	 * 		Los registros de la página actual.
	 */
	List<T> getDatos();

	/**
	 * @param datos
	 * 		Los registros de la página actual.
	 */
	void setDatos(List<T> datos);

	/**
	 * Devuelve el índice del primer registro de la página actual.
	 * 
	 * @return
	 */
	int getPrimerRegistro();

	/**
	 * Establece el número total de registros.
	 * 
	 * @param numeroTotalRegistros
	 */
	void setNumeroTotalRegistros(int numeroTotalRegistros);

	/**
	 * Establece el filtro de búsqueda de la consulta.
	 * 
	 * @param filtro
	 */
	void setFiltro(IFiltro filtro);

	/**
	 * Establece la página actual.
	 * 
	 * @param pagina
	 */
	void setPagina(int pagina);

	/**
	 * Establece el número total de registros por página.
	 * 
	 * @param numeroRegistrosPorPagina
	 */
	void setNumeroTotalRegistrosPorPagina(int numeroRegistrosPorPagina);

	/**
	 * Obtiene el filtro de la búsqueda paginada.
	 * 
	 * @return
	 */
	IFiltro getFiltro();
}
