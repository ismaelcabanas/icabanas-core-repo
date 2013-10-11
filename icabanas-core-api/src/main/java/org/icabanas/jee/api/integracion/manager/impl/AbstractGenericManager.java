package org.icabanas.jee.api.integracion.manager.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.icabanas.jee.api.integracion.dao.DaoException;
import org.icabanas.jee.api.integracion.dao.IFiltro;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.icabanas.jee.api.integracion.dao.IPaginador;
import org.icabanas.jee.api.integracion.dao.Pagina;
import org.icabanas.jee.api.integracion.dto.BaseDto;
import org.icabanas.jee.api.integracion.manager.IGenericManager;
import org.icabanas.jee.api.integracion.manager.exceptions.EliminarException;
import org.icabanas.jee.api.integracion.manager.exceptions.ModificarException;
import org.icabanas.jee.api.integracion.manager.exceptions.NoExisteEntidadException;
import org.icabanas.jee.api.integracion.manager.exceptions.PaginacionException;
import org.icabanas.jee.api.integracion.manager.exceptions.RegistrarException;
import org.icabanas.jee.api.integracion.manager.exceptions.ValidacionException;
import org.icabanas.jee.api.integracion.modelo.Entidad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractGenericManager<Id extends Serializable, Dto extends BaseDto<Id>, En extends Entidad<Id>> 
		implements IGenericManager<Id, Dto> {

	private static Logger logger = LoggerFactory.getLogger(AbstractGenericManager.class);
	
	private IGenericDao<Id, En> dao;
	
	@Override
	public Dto registrar(Dto dto) throws RegistrarException {
		logger.debug("Registrando la entidad: " + dto);
		
		// compruebo el paráemtro de entrada
		if(dto == null){
			throw new IllegalArgumentException("El DTO de entrada no puede ser nulo.");
		}
		
		// creo la instancia de la entidad
		En entidad = null;
		try{
			entidad = crearEntidad(dto);
		}
		catch(ValidacionException e){
			logger.error("Dto no válido: " + dto);
			throw new RegistrarException(e);
		}
		
		// persisto la entidad
		dao.crear(entidad);
		
		// adapto la entidad persistida a Dto
		Dto dtoPersistido = adaptarToDto(entidad);
		
		return dtoPersistido;
	}	

	@Override
	public Dto actualizar(Dto dto) throws ModificarException,
			NoExisteEntidadException {
		logger.debug("Modificando la entidad: " + dto);
		if(dto == null){
			throw new IllegalArgumentException("El DTO de entrada no puede ser nulo.");
		}
		if(dto.getId() == null){
			throw new IllegalArgumentException("El identificador de la entidad no puede ser nulo.");
		}
		
		// busco la entidad a modificar
		En entidad = dao.buscarPorId(dto.getId());
		
		// si existe la entidad la modifico, en caso contrario lanzo una excepción
		if(entidad != null){
			try {
				actualizarEntidad(entidad,dto);
			} catch (ValidacionException e) {
				throw new ModificarException(e);
			}
		}
		else{
			logger.error("Error al modificar la entidad");
			throw new NoExisteEntidadException("La entidad con identificador " + dto.getId()
					+ " no existe.");
		}
		
		// actualizo la entidad
		En entidadModificada = dao.modificar(entidad);
		
		// transformo y devuelvo el dto que representa a la entidad
		Dto resultado = adaptarToDto(entidadModificada);
		logger.debug("Producto modificado " + resultado);
		
		return resultado;
	}

	@Override
	public void eliminar(Id id) throws NoExisteEntidadException, EliminarException {
		logger.debug("Eliminando la entidad con identificador " + id);
		En entidad = dao.buscarPorId(id);
		if(entidad != null){
			dao.eliminar(entidad);
			logger.debug("Entidad eliminada correctamente.");
		}		
		else{
			logger.error("Error al eliminar la entidad");
			throw new NoExisteEntidadException("La entidad con identificador " + id 
					+ " no existe.");
		}
	}

	@Override
	public Dto buscarPorId(Id id) throws NoExisteEntidadException {
		logger.debug("Buscando entidad por identificador " + id);
		if(id == null){
			throw new NoExisteEntidadException("El identificador de la entidad es nulo.");
		}		

		En entidad = dao.buscarPorId(id);
		logger.debug("Entidad recuperada..." + entidad);
						
		Dto resultado =  adaptarToDto(entidad);
		return resultado;
	}

	@Override
	public void paginar(Pagina<Dto> pagina) throws PaginacionException {
		IPaginador<En> paginador = null;
		Pagina<En> pag = new Pagina<En>(pagina.getPagina(),pagina.getNumeroRegistrosPorPagina(), pagina.getFiltro());
		
		try{
			paginador = dao.paginar(pag);
		}
		catch(DaoException ex){
			throw new PaginacionException("Error en la paginación.", ex);
		}
		
		List<Dto> datos = new ArrayList<Dto>();
		for (En en : paginador.getDatos()) {
			datos.add(adaptarToDto(en));
		}
		
		pagina.setDatos(datos);
		pagina.setNumeroTotalRegistros(paginador.getNumeroTotalRegistros());
	}
	
	@Override
	public boolean validar(Dto dto) throws ValidacionException {
		logger.debug("Validando la entidad " + dto);
		if(dto == null){
			throw new IllegalArgumentException("El parámetro de entrada no puede ser nulo.");
		}
		
		En entidad = adaptarToEntidad(dto);
		
		boolean resultado = entidad.valida();
		logger.debug("El resultado de la validación ha sido " + resultado);
		
		return resultado;
	}	
		
	@Override
	public void limpiarFiltro(IFiltro filtro) {
		filtro.limpiar();
	}
		
	public IGenericDao<Id, En> getDao() {
		return dao;
	}

	public void setDao(IGenericDao<Id, En> dao) {
		this.dao = dao;
	}

	
	/**
	 * Adapta la entidad persistida a un Dto.
	 * 
	 * @param entidad
	 * 		La entidad
	 * @return
	 * 		El Dto.
	 */
	protected abstract Dto adaptarToDto(En entidad);

	/**
	 * Adapta un Dto a su correspondiente entidad.
	 * 
	 * @param dto
	 * @return
	 */
	protected abstract En adaptarToEntidad(Dto dto);
	
	/**
	 * Crea una instancia de la entidad a partir del Dto. 
	 * 
	 * Antes de crear de persistir la instancia se comprueba si existe la unicidad de la instancia, es decir, 
	 * si fue persistida anteriormente.
	 * 
	 * @param dto
	 * 		El Dto.
	 * @return
	 * 		La entidad.
	 * @throws ValidacionException
	 * 		Si se produce un error de validación al crear la entidad o la entidad existía con anterioridad.
	 */
	protected abstract En crearEntidad(Dto dto) throws ValidacionException;
	
	/**
	 * Actualiza una instancia de la entidad a partir del Dto.
	 * @param entidad 
	 * 
	 * @param dto
	 * 		El Dto.
	 * @throws ValidacionException
	 * 		Si se produce un error de validación al actualizar la entidad
	 */
	protected abstract void actualizarEntidad(En entidad, Dto dto) throws ValidacionException;
	
	
}
