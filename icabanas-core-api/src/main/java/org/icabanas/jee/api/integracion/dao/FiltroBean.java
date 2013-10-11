package org.icabanas.jee.api.integracion.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * Clase que implementa la interfaz {@link IFiltro}.
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
public abstract class FiltroBean implements IFiltro, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Orden> orden;
	private String nombreFiltro;

	
	public FiltroBean() {
		this.nombreFiltro = StringUtils.uncapitalize(this.getClass().getSimpleName());
	}

	public FiltroBean(String nombreFiltro) {
		super();
		this.nombreFiltro = nombreFiltro;
	}	

	public List<Orden> getOrden() {
		return orden;
	}

	public void setOrden(List<Orden> orden) {
		this.orden = orden;
	}

	@Override
	public String getNombreFiltro() {
		return nombreFiltro;
	}

	public void setNombreFiltro(String nombre) {
		this.nombreFiltro = nombre;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombreFiltro == null) ? 0 : nombreFiltro.hashCode());
		result = prime * result + ((orden == null) ? 0 : orden.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FiltroBean other = (FiltroBean) obj;
		if (nombreFiltro == null) {
			if (other.nombreFiltro != null)
				return false;
		} else if (!nombreFiltro.equals(other.nombreFiltro))
			return false;
		if (orden == null) {
			if (other.orden != null)
				return false;
		} else if (!orden.equals(other.orden))
			return false;
		return true;
	}

	
	
}
