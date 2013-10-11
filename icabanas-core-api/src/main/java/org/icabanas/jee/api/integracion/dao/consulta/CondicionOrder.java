package org.icabanas.jee.api.integracion.dao.consulta;

/**
 * <br/><br/>
 * <b>Responsabilidad</b> : Representa uno de los componentes de la clausula ORDER BY de una consulta.  
 * <br/>
 * <br/>
 * 
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
public class CondicionOrder {

	private String campo;
	private OrderEnum tipoOrden;

	public CondicionOrder(String campo, OrderEnum tipoOrden) {
		this.campo = campo;
		this.tipoOrden = tipoOrden;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public OrderEnum getTipoOrden() {
		return tipoOrden;
	}

	public void setTipoOrden(OrderEnum tipoOrden) {
		this.tipoOrden = tipoOrden;
	}

	@Override
	public String toString() {
		return campo + " " + tipoOrden.getValue();
	}

	
}
