package org.icabanas.jee.api.integracion.dao.consulta;

/**
 * <br/><br/>
 * <b>Responsabilidad</b> : Enumeración que indica los distintos tipos de orden que se pueden aplicar a 
 * la cláusula ORDER BY.  
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
public enum OrderEnum {
	ASC("ASC") // establece orden ascendente
	, DESC("DESC"); // establece orden descendente

	private String value;
		
	
	private OrderEnum(String value) {
		this.value = value;
	}


	public String getValue() {
		return this.value;
	}

}
