package org.icabanas.jee.api.integracion.manager;

import java.io.Serializable;

import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.Pagina;
import org.icabanas.jee.api.integracion.manager.exceptions.EliminarException;
import org.icabanas.jee.api.integracion.manager.exceptions.ModificarException;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.icabanas.jee.api.integracion.manager.exceptions.PaginacionException;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;

public interface IGenericManager<Id extends Serializable, Dto extends Serializable> {

	/**
	 * Método que registra una entidad en el catálogo.
	 * 
	 * @param dto
	 * 		La entidad a registrar
	 * @return
	 * 		La entidad registrada
	 * @throws RegistrarException
	 * 		Si se produce alguna excepción al registrar la entidad
	 */
	Dto registrar(Dto dto) throws RegistrarException;
	
	/**
	 * Método que actualiza las características de una entidad.
	 * 
	 * @param 
	 * 		dto Las nuevas características de la entidad.
	 * 
	 * @return La entidad actualizada.
	 * 
	 * @throws 
	 * 		NoExisteEntidadException Si la entidad no existe en sistema de almacenamiento.
	 * 
	 * @throws 
	 * 		ModificarException Si se produce alguna excepción de validación al actualizar la entidad. 	 
	 */
	Dto actualizar(Dto dto) throws ModificarException, NoExisteEntidadException;
	
	/**
	 * Método que elimina una entidad del catálogo.
	 * 
	 * @param 
	 * 		id Identificador de la entidad
	 * @throws 
	 * 		NoExisteEntidadException si no existe la entidad
	 * @throws 
	 * 		EliminarException si se produce algún error al eliminar la entidad
	 */
	void eliminar(Id id) throws NoExisteEntidadException, EliminarException;
	
	/**
	 * Método que realiza una búsqueda de entidad por identificador.
	 * 
	 * @param 
	 * 		id el identificador de la entidad
	 * 
	 * @return La entidad
	 *  
	 * @throws 
	 * 		NoExisteEntidadException si la entidad no existe
	 */
	Dto buscarPorId(Id id) throws NoExisteEntidadException;
	
	/**
	 * Realiza una búsqueda paginada en base a un criterio de búsqueda definido en el objeto {@link Pagina}.
	 * 
	 * @param pagina
	 * @throws PaginacionException
	 * 		Si se produce alguna excepción durante la paginación.
	 */
	void paginar(Pagina<Dto> pagina) throws PaginacionException;
	
	/**
	 * Método que comprueba si el dto es válido
	 * 
	 * @param 
	 * 		dto El Dto a validar.
	 * 
	 * @return 
	 * 		True si es Dto es válido
	 * 
	 * @throws 
	 * 		ValidacionException Si el Dto no es válido, indicando qué campos son los incorrectos.
	 * 
	 * @throws 
	 * 		IllegalArgumentException Si el parámetro de entrada es nulo.
	 */
	boolean validar(Dto dto) throws ValidacionException;

	/**
	 * Limpia el filtro de búsqueda, es decir, establece los valores del filtro a su valor por defecto.
	 * 
	 * @param filtro
	 * 		El filtro de búsqueda.
	 */
	void limpiarFiltro(IFiltro filtro);	
}
