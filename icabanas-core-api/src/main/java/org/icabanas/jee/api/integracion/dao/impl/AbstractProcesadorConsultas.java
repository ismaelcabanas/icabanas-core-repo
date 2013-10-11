package org.icabanas.jee.api.integracion.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.icabanas.jee.api.integracion.dao.IProcesadorConsultas;
import org.icabanas.jee.api.integracion.dao.consulta.CondicionOrder;
import org.icabanas.jee.api.integracion.dao.consulta.CondicionWhere;
import org.icabanas.jee.api.integracion.dao.consulta.OrderEnum;
import org.icabanas.jee.api.integracion.dao.consulta.OperadorWhereEnum;

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
 * @param <Entidad>
 */
public abstract class AbstractProcesadorConsultas<Entidad> implements
		IProcesadorConsultas<Entidad> {

	protected Class<Entidad> persistentClass;
	private List<CondicionOrder> clausulaOrderBy;
	private List<CondicionWhere> clausulaWhere;

	
	/**
	 * Inicializa las variables de instancia. El constructor de esta clae debe llamarse siempre desde 
	 * las clases hijas.
	 */
	public AbstractProcesadorConsultas() {
		super();
		this.clausulaOrderBy = new ArrayList<CondicionOrder>();
		this.clausulaWhere = new ArrayList<CondicionWhere>();
	}

	
	
	@Override
	public void setPersistentClass(Class<Entidad> persistentClass) {
		this.persistentClass = persistentClass;
	}

//	@Override
	public void nuevaConsulta() {
		this.clausulaOrderBy = new ArrayList<CondicionOrder>();
		this.clausulaWhere = new ArrayList<CondicionWhere>();
	}

	
	@Override
	public final void ordenarPorAsc(String campo) {
		CondicionOrder condicionOrderAsc = new CondicionOrder(campo,OrderEnum.ASC);
		clausulaOrderBy.add(condicionOrderAsc);
	}
	
	@Override
	public final void ordenarPorDesc(String campo) {
		CondicionOrder condicionOrderDesc = new CondicionOrder(campo,OrderEnum.DESC);
		clausulaOrderBy.add(condicionOrderDesc);
	}

	@Override
	public final void andIgualQue(String campo, Object valor) {
		CondicionWhere condicionWhere = new CondicionWhere(campo,OperadorWhereEnum.OP_EQUAL,valor);
		clausulaWhere.add(condicionWhere);
	}

	@Override
	public final void andLike(String campo, Object valor) {
		CondicionWhere condicionWhere = new CondicionWhere(campo,OperadorWhereEnum.OP_LIKE,valor);
		clausulaWhere.add(condicionWhere);
	}
	
	public List<CondicionOrder> getCondicionesOrderBy() {
		return clausulaOrderBy;
	}

	public List<CondicionWhere> getCondicionesWhere() {
		return clausulaWhere;
	}
}
