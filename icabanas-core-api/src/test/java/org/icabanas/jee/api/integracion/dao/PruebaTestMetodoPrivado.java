package org.icabanas.jee.api.integracion.dao;

public class PruebaTestMetodoPrivado {

	private int x;
	private int y;

	public PruebaTestMetodoPrivado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int prueba(){
		int resultado = _prueba();
		
		return resultado;
	}

	private int _prueba() {
		return x*y;
	}
}
