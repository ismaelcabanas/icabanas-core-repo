package org.icabanas.jee.api.integracion.dao;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

public class ResultadoPaginadoTest {

	@Test
	public void deberia_crear_instancia_sin_elementos(){
		// preparación		
		List<String> elementos = new ArrayList<String>();
		
		// ejecución
		ResultadoPaginado<String> test = new ResultadoPaginado<String>(elementos);
		
		// verificación
		Assert.assertThat(test.getElementos(), IsEqual.equalTo(elementos));
		Assert.assertThat(test.getNumeroTotalPaginas(), IsEqual.equalTo(-1));
		Assert.assertThat(test.getPaginaActual(), IsEqual.equalTo(-1));
		Assert.assertThat(test.esVacio(), IsEqual.equalTo(Boolean.TRUE));
	}
	
	@Test
	public void deberia_crear_instancia_con_elementos(){
		// preparación		
		List<String> elementos = new ArrayList<String>();
		elementos.add("test");
		
		// ejecución
		ResultadoPaginado<String> test = new ResultadoPaginado<String>(elementos);
		
		// verificación
		Assert.assertThat(test.getElementos(), IsEqual.equalTo(elementos));
		Assert.assertThat(test.getNumeroTotalPaginas(), IsEqual.equalTo(-1));
		Assert.assertThat(test.getPaginaActual(), IsEqual.equalTo(-1));
		Assert.assertThat(test.esVacio(), IsEqual.equalTo(Boolean.FALSE));
	}
}
