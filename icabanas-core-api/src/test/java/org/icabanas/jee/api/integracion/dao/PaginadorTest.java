package org.icabanas.jee.api.integracion.dao;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

public class PaginadorTest {

	@Test
	public void deberia_crear_instancia_paginador(){
		// preparación
		
		
		// ejecución
		IPaginador<String> paginador = new Pagina<String>();		
		
		// verificación
		Assert.assertThat(paginador.getPagina(), IsEqual.equalTo(1));
		Assert.assertThat(paginador.getNumeroRegistrosPorPagina(), IsEqual.equalTo(5));
		Assert.assertThat(paginador.getNumeroTotalPaginas(), IsEqual.equalTo(0));
		Assert.assertThat(paginador.getNumeroTotalRegistros(), IsEqual.equalTo(0));
		Assert.assertThat(paginador.getDatos().size(), IsEqual.equalTo(0));
	}
}
