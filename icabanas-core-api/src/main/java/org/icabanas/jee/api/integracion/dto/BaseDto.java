package org.icabanas.jee.api.integracion.dto;

import java.io.Serializable;

public abstract class BaseDto<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = -1423822661061660125L;
	
	private ID id;

	public BaseDto(ID id) {
		this.id = id;
	}

	public BaseDto() {
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}	
}
