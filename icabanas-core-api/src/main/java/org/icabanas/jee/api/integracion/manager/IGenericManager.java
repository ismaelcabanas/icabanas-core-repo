package org.icabanas.jee.api.integracion.manager;

import java.io.Serializable;
import java.util.List;

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
	 * M�todo que registra una entidad en el cat�logo.
	 * 
	 * @param dto
	 * 		La entidad a registrar
	 * @return
	 * 		La entidad registrada
	 * @throws RegistrarException
	 * 		Si se produce alguna excepci�n al registrar la entidad
	 */
	Dto registrar(Dto dto) throws RegistrarException;
	
	/**
	 * M�todo que actualiza las caracter�sticas de una entidad.
	 * 
	 * @param 
	 * 		dto Las nuevas caracter�sticas de la entidad.
	 * 
	 * @return La entidad actualizada.
	 * 
	 * @throws 
	 * 		NoExisteEntidadException Si la entidad no existe en sistema de almacenamiento.
	 * 
	 * @throws 
	 * 		ModificarException Si se produce alguna excepci�n de validaci�n al actualizar la entidad. 	 
	 */
	Dto actualizar(Dto dto) throws ModificarException, NoExisteEntidadException;
	
	/**
	 * M�todo que elimina una entidad del cat�logo.
	 * 
	 * @param 
	 * 		id Identificador de la entidad
	 * @throws 
	 * 		NoExisteEntidadException si no existe la entidad
	 * @throws 
	 * 		EliminarException si se produce alg�n error al eliminar la entidad
	 */
	void eliminar(Id id) throws NoExisteEntidadException, EliminarException;
	
	/**
	 * M�todo que realiza una b�squeda de entidad por identificador.
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
	 * Realiza una b�squeda paginada en base a un criterio de b�squeda definido en el objeto {@link Pagina}.
	 * 
	 * @param pagina
	 * @throws PaginacionException
	 * 		Si se produce alguna excepci�n durante la paginaci�n.
	 */
	void paginar(Pagina<Dto> pagina) throws PaginacionException;
	
	/**
	 * Devuelve todos los registros convertidos en Dtos de una entidad.
	 * 
	 * @return
	 * 		Lista de Dtos
	 */
	List<Dto> buscarTodas();
	
	/**
	 * Devuelve todos los registros convertidos en Dtos de una entidad en base a un criterio de búsqueda.
	 * 
	 * @param filtro
	 * 		El filtro de búsqueda
	 * @return
	 * 		Lista de Dtos		
	 */
	List<Dto> buscar(IFiltro filtro);
	
	/**
	 * M�todo que comprueba si el dto es v�lido
	 * 
	 * @param 
	 * 		dto El Dto a validar.
	 * 
	 * @return 
	 * 		True si es Dto es v�lido
	 * 
	 * @throws 
	 * 		ValidacionException Si el Dto no es v�lido, indicando qu� campos son los incorrectos.
	 * 
	 * @throws 
	 * 		IllegalArgumentException Si el par�metro de entrada es nulo.
	 */
	boolean validar(Dto dto) throws ValidacionException;

	/**
	 * Limpia el filtro de b�squeda, es decir, establece los valores del filtro a su valor por defecto.
	 * 
	 * @param filtro
	 * 		El filtro de b�squeda.
	 */
	void limpiarFiltro(IFiltro filtro);	
}
