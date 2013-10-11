package org.icabanas.jee.api.integracion.dao.jpa;

import org.icabanas.jee.api.integracion.dao.impl.jpa.AbstractGenericJPADao;
import org.icabanas.jee.api.integracion.entidad.Persona;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaDaoImpl extends AbstractGenericJPADao<Long, Persona> {

}
