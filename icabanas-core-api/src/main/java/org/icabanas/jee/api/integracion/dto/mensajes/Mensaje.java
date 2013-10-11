package org.icabanas.jee.api.integracion.dto.mensajes;

import java.io.Serializable;

/**
 * <br/><br/>
 * <b>Responsabilidad</b>:   
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoMensaje tipoMensaje;
	
	private String codigoMensaje;
	
	/**
	 * Key del mensaje del fichero internacionalizado
	 */
	private String i18NKeyMensaje;
	
	/**
	 * Mensaje traducido
	 */
	private String i18NMensaje;

	public Mensaje(TipoMensaje tipo, String i18NKeyMensaje) {
		this.tipoMensaje = tipo;
		this.i18NKeyMensaje = i18NKeyMensaje;
	}

	public TipoMensaje getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(TipoMensaje tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	public String getI18NKeyMensaje() {
		return i18NKeyMensaje;
	}

	public void setI18NKeyMensaje(String i18nKeyMensaje) {
		i18NKeyMensaje = i18nKeyMensaje;
	}

	public String getI18NMensaje() {
		return i18NMensaje;
	}

	public void setI18NMensaje(String i18nMensaje) {
		i18NMensaje = i18nMensaje;
	}
	
	
}
