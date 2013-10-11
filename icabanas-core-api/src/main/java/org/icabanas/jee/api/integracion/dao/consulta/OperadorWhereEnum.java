package org.icabanas.jee.api.integracion.dao.consulta;

public enum OperadorWhereEnum {
	OP_EQUAL("="), OP_AND("AND"), OP_OR("OR"), OP_IS_NULL("IS NULL"), OP_IS_NOT_NULL("IS NOT NULL"), 
	OP_NOT_EQUAL("!="), OP_GREATER_THAN(">"), OP_LIKE("LIKE");
	
	private String value;

	private OperadorWhereEnum(String value) {
		this.value = value;
	}

	public String getOperador() {
		return value;
	}		

}
