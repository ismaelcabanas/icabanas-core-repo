package org.icabanas.jee.api.integracion.dao;

import org.icabanas.jee.api.integracion.dao.consulta.OrderEnum;

/**
 * Clase que representa la ordenación de una consulta.
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
public class Orden {

	private String campo;
	private OrderEnum orden;

	public Orden(String campo, OrderEnum orden) {
		this.campo = campo;
		this.orden = orden;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public OrderEnum getOrden() {
		return orden;
	}

	public void setOrden(OrderEnum orden) {
		this.orden = orden;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
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
		Orden other = (Orden) obj;
		if (campo == null) {
			if (other.campo != null)
				return false;
		} else if (!campo.equals(other.campo))
			return false;
		if (orden != other.orden)
			return false;
		return true;
	}

	
}
