package org.icabanas.zk.componentes.html5;

import java.io.IOException;

import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.A;

public class AHTML5 extends A implements IHTML5Componente {

	private static final long serialVersionUID = 1L;

	private String _dataAttribute;
	
	private String _dataValue;

	public String getDataAttribute() {
		return _dataAttribute;
	}

	public void setDataAttribute(String _dataAttribute) {
		this._dataAttribute = _dataAttribute;
		
		// esto llamar� al m�todo setDataAttribute(_dataAttribute) del Widget del lado cliente
		smartUpdate(DATA_ATTRIBUTE, this._dataAttribute);
	}

	public String getDataValue() {
		return _dataValue;
	}

	public void setDataValue(String _dataValue) {
		this._dataValue = _dataValue;
		
		// esto llamar� al m�todo setDataValue(_dataValue) del Widget del lado cliente
		smartUpdate(DATA_VALUE, this._dataValue);
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zul.Button#renderProperties(org.zkoss.zk.ui.sys.ContentRenderer)
	 */
	@Override
	protected void renderProperties(ContentRenderer renderer)
			throws IOException {
		// este m�todo se llamar� autom�ticamente por el framework, pero hay que llamar primero al super()
		super.renderProperties(renderer);
		
		// esto llamar� al m�todo setDataAttribute del Widget de la parte cliente
		if(_dataAttribute != null){
			render(renderer, DATA_ATTRIBUTE, _dataAttribute);
		}
		// esto llamar� al m�todo setDataValue del Widget de la parte cliente
		if(_dataValue != null){
			render(renderer, DATA_VALUE, _dataValue);
		}
	}


}
