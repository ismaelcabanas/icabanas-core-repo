package org.icabanas.zk.componentes.html5;

/**
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
public interface IHTML5Componente {

	public static final String DATA_VALUE = "dataValue";

	public static final String DATA_ATTRIBUTE = "dataAttribute";

	
	public String getDataAttribute();

	public void setDataAttribute(String _dataAttribute);
	
	public String getDataValue();

	public void setDataValue(String _dataValue);
}
