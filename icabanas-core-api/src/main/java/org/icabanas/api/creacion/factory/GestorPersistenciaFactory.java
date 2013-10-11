package org.icabanas.api.creacion.factory;

import org.icabanas.jee.api.integracion.dao.IGestorPersistencia;

public abstract class GestorPersistenciaFactory implements IFactoryMethod<IGestorPersistencia> {

	@Override
	public abstract IGestorPersistencia crear();

}
