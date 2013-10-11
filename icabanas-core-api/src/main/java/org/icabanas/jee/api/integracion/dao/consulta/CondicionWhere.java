package org.icabanas.jee.api.integracion.dao.consulta;

public class CondicionWhere {

	private String campo;
	private OperadorWhereEnum operador;
	private Object valor;

	public CondicionWhere(String campo, OperadorWhereEnum operador, Object valor) {
		this.campo = campo;
		this.operador = operador;
		this.valor = valor;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public OperadorWhereEnum getOperador() {
		return operador;
	}

	public void setOperador(OperadorWhereEnum operador) {
		this.operador = operador;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	
}
