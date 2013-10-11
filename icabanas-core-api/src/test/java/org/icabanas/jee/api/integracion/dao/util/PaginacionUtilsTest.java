package org.icabanas.jee.api.integracion.dao.util;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

public class PaginacionUtilsTest {

	@Test
	public void el_primer_registro_de_la_pagina_2_de_un_total_de_8_registros_es_el_4(){
		// preparación		
		int paginaActual = 2;
		int numeroRegistrosPorPagina = 3;
		
		// ejecución
		int resultado = PaginacionUtils.calculaPrimerRegistro(paginaActual, numeroRegistrosPorPagina);
		
		// verificación
		Assert.assertThat(resultado, IsEqual.equalTo(4));
	}
	
	@Test
	public void el_primer_registro_de_la_pagina_3_de_un_total_de_8_registros_es_el_7(){
		// preparación		
		int paginaActual = 3;
		int numeroRegistrosPorPagina = 3;
		
		// ejecución
		int resultado = PaginacionUtils.calculaPrimerRegistro(paginaActual, numeroRegistrosPorPagina);
		
		// verificación
		Assert.assertThat(resultado, IsEqual.equalTo(7));
	}
	
	@Test
	public void deberia_lanzar_excepcion_si_calcula_primer_registro_y_la_pagina_actual_es_menor_que_1(){
		// preparación		
		int numeroRegistrosPorPagina = 3;
		
		// ejecución
		try{
			int resultado = PaginacionUtils.calculaPrimerRegistro(0, numeroRegistrosPorPagina);
			Assert.fail("Debería lanzar excepción");
		}
		catch(IllegalArgumentException e){}
		
		try{
			int resultado = PaginacionUtils.calculaPrimerRegistro(-4, numeroRegistrosPorPagina);
			Assert.fail("Debería lanzar excepción");
		}
		catch(IllegalArgumentException e){}
		
		// verificación
		Assert.assertTrue(true);
	}
	
	@Test
	public void deberia_lanzar_excepcion_si_calcula_primer_registro_y_numero_registros_por_pagina_es_menor_que_1(){
		// preparación		
		int paginaActual = 3;
		
		// ejecución
		try{
			int resultado = PaginacionUtils.calculaPrimerRegistro(paginaActual, 0);
			Assert.fail("Debería lanzar excepción");
		}
		catch(IllegalArgumentException e){}
		
		try{
			int resultado = PaginacionUtils.calculaPrimerRegistro(paginaActual, -1);
			Assert.fail("Debería lanzar excepción");
		}
		catch(IllegalArgumentException e){}
		
		// verificación
		Assert.assertTrue(true);
	}
	
	@Test
	public void si_numregppag_es_3_y_numtotreg_es_8_el_numero_total_paginas_es_3(){
		// preparación
		int numregppag = 3;
		int numtotreg = 8;
		
		// ejecución
		int numtotpag = PaginacionUtils.calculaTotalPaginas(numregppag, numtotreg);
		
		// verificación
		Assert.assertThat(numtotpag, IsEqual.equalTo(3));
	}
	
	@Test
	public void si_numregppag_es_mayor_que_numtotreg_el_numero_total_paginas_es_1(){
		// preparación
		int numregppag = 3;
		int numtotreg = 1;
		
		// ejecución
		int numtotpag = PaginacionUtils.calculaTotalPaginas(numregppag, numtotreg);
		
		// verificación
		Assert.assertThat(numtotpag, IsEqual.equalTo(1));
	}
	
	@Test
	public void si_numregppag_es_3_y_numtotreg_es_0_el_numero_total_paginas_es_0(){
		// preparación
		int numregppag = 3;
		int numtotreg = 0;
		
		// ejecución
		int numtotpag = PaginacionUtils.calculaTotalPaginas(numregppag, numtotreg);
		
		// verificación
		Assert.assertThat(numtotpag, IsEqual.equalTo(0));
	}
	
	@Test
	public void deberia_lanzar_excepcion_si_numregppag_es_menorIgual_que_0_y_numtotreg_es_mayor_que_0(){
		// preparación		
		
		// ejecución
		try{
			int numtotpag = PaginacionUtils.calculaTotalPaginas(0, 8);
			Assert.fail("Debería lanzar excepción");
		}
		catch(IllegalArgumentException e){}
		
		try{
			int resultado = PaginacionUtils.calculaTotalPaginas(-1, 8);
			Assert.fail("Debería lanzar excepción");
		}
		catch(IllegalArgumentException e){}
		
		// verificación
		Assert.assertTrue(true);
	}
	
	@Test
	public void deberia_lanzar_excepcion_si_numtotreg_es_menor_que_0(){
		// preparación		
		
		// ejecución
		try{
			int numtotpag = PaginacionUtils.calculaTotalPaginas(3, -1);
			Assert.fail("Debería lanzar excepción");
		}
		catch(IllegalArgumentException e){}
				
		// verificación
		Assert.assertTrue(true);
	}
}
