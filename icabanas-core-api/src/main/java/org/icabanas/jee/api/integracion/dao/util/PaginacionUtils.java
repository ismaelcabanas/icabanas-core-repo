package org.icabanas.jee.api.integracion.dao.util;

import org.apache.commons.lang.Validate;

/**
 * Clase de utilidad para operaciones sobre la paginación. 
 * 
 * <br/><br/>
 * <b>Responsabilidad</b> :  Expone métodos de utilidad para la paginación de registros.
 * 
 * @author f009994r
 *
 */
public final class PaginacionUtils {

	
	private PaginacionUtils() {}

	/**
	 * Calcula la posición del primer registro de una página determinada en un listado de registros paginados.
	 * 
	 * @param paginaActual
	 * 		Página actual
	 * @param numeroRegistrosPorPagina
	 * 		Número de registros por página.
	 * 
	 * @return
	 * 		La posición del primer registro de una página en un listado de registros paginados.
	 */
	public static int calculaPrimerRegistro(int paginaActual,
			int numeroRegistrosPorPagina) {		
		int primerRegistro;
		
		Validate.isTrue(paginaActual >= 1, "El parámetro paginaActual debe ser mayor o igual que 1.");
		Validate.isTrue(numeroRegistrosPorPagina >= 1, "El parámetro numeroRegistrosPorPagina debe ser mayor o igual que 1.");
		
		primerRegistro = ((paginaActual - 1) * numeroRegistrosPorPagina) + 1;
		
		return primerRegistro;
	}

	/**
	 * Calcula el número de páginas que habrá en un listado de registros.
	 * 
	 * @param numeroRegistrosPorPagina
	 * 		Número de registros por página
	 * @param numeroTotalRegistros
	 * 		Número total de registros.
	 * 
	 * @return
	 * 		El número total de páginas de un listado de registros.
	 */
	public static int calculaTotalPaginas(int numeroRegistrosPorPagina,
			int numeroTotalRegistros) {
		
		Validate.isTrue(numeroRegistrosPorPagina >=1,  "El parámetro numeroRegistrosPorPagina debe ser >= que 1.");
		Validate.isTrue(numeroTotalRegistros >=0,  "El parámetro numeroTotalRegistros debe ser >= que 0.");
		
		int numeroTotalPaginas;
		if(numeroTotalRegistros % numeroRegistrosPorPagina == 0){
			numeroTotalPaginas = numeroTotalRegistros / numeroRegistrosPorPagina;
		}
		else{
			numeroTotalPaginas = (numeroTotalRegistros / numeroRegistrosPorPagina) + 1;
		}
		return numeroTotalPaginas;
	}

}
