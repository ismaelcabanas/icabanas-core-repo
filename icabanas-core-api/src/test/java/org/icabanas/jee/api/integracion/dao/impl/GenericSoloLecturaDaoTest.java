package org.icabanas.jee.api.integracion.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.jee.api.integracion.dao.DaoException;
import org.icabanas.jee.api.integracion.dao.IGestorPersistencia;
import org.icabanas.jee.api.integracion.entidad.Persona;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GenericSoloLecturaDaoTest {
	
	private GenericSoloLecturaDao<Long, Persona> _dao;
	
	@Mock
	private IGestorPersistencia _mockGestorPersistencia;
	
//	@Mock
//	private IProcesadorConsultas<Persona> _mockProcesadorConsultas;
		
	@Before
	public void configurarTest(){
		_dao = new PersonaDaoImpl();
//		_mockGestorPersistencia.setProcesadorConsultas(_mockProcesadorConsultas);
//		Mockito.when(_mockGestorPersistencia.getProcesadorConsultas()).thenReturn(_mockProcesadorConsultas);
		_dao.setGestorPersistencia(_mockGestorPersistencia);		
//		_dao.setProcesadorConsultas(_mockProcesadorConsultas);
	}
	
	@Test
	public void deberia_crear_instancia(){
		// preparaci�n y ejecuci�n
		GenericSoloLecturaDao<Long, Persona> dao = new PersonaDaoImpl();
		
		//verificaci�n
		Assert.assertThat(dao, IsNull.notNullValue());
		Assert.assertThat(dao.getGestorPersistencia(), IsNull.nullValue());
		Assert.assertThat(dao.getPersistentClass(), IsEqual.equalTo(Persona.class));
//		Assert.assertThat(dao.getProcesadorConsultas(), IsNull.nullValue());
	}
	
	@Test
	public void deberia_crear_instancia_con_gestor_persistencia(){
		// preparaci�n y ejecuci�n
		GenericSoloLecturaDao<Long, Persona> dao = new PersonaDaoImpl();
		dao.setGestorPersistencia(_mockGestorPersistencia);		
//		dao.setProcesadorConsultas(_mockProcesadorConsultas);
//		Mockito.when(_mockGestorPersistencia.getProcesadorConsultas()).thenReturn(_mockProcesadorConsultas);
		
		//verificaci�n
		Assert.assertThat(dao, IsNull.notNullValue());
		Assert.assertThat(dao.getGestorPersistencia(), IsNull.notNullValue());
//		Assert.assertThat(dao.getProcesadorConsultas(), IsNull.notNullValue());
		Assert.assertThat(dao.getPersistentClass(), IsEqual.equalTo(Persona.class));
	}
	
	@Test(expected=DaoException.class)
	public void deberia_lanzar_excepcion_si_buscar_por_identificador_nulo(){
		// preparaci�n
		Long id = null;
		Mockito.when(_mockGestorPersistencia.buscarPorId(id, Persona.class));
		
		// ejecuci�n
		_dao.buscarPorId(id);
		
		// verificaci�n		
	}
	
	@Test
	public void deberia_buscar_por_identificador(){
		// preparaci�n
		Long id = 1L;
		Persona entidad = new Persona();
		Mockito.when(_mockGestorPersistencia.buscarPorId(id, Persona.class)).thenReturn(entidad);
		
		// ejecuci�n
		Persona test = _dao.buscarPorId(id);
		
		// verificaci�n		
		Mockito.verify(_mockGestorPersistencia, VerificationModeFactory.times(1)).buscarPorId(id, Persona.class);
		Assert.assertThat(test, IsEqual.equalTo(entidad));
	}
	
	@Test
	public void deberia_devolver_todos_los_registros_sin_paginar(){
		// preparaci�n
		List<Persona> listaRegistros = new ArrayList<Persona>();
		listaRegistros.add(new Persona("1"));
		listaRegistros.add(new Persona("2"));
		listaRegistros.add(new Persona("3"));
		listaRegistros.add(new Persona("4"));
		listaRegistros.add(new Persona("5"));
		listaRegistros.add(new Persona("6"));
		listaRegistros.add(new Persona("7"));
		listaRegistros.add(new Persona("8"));
		Mockito.when(_mockGestorPersistencia.buscarTodos(Persona.class)).thenReturn(listaRegistros);
		
		// ejecuci�n
		List<Persona> resultado = _dao.buscarTodos();
		
		// verificaci�n
		Mockito.verify(_mockGestorPersistencia,VerificationModeFactory.times(1)).buscarTodos(Persona.class);
		Assert.assertThat(resultado, IsNull.notNullValue());
		Assert.assertThat(resultado, IsNull.notNullValue());
		Assert.assertThat(resultado.size(), IsEqual.equalTo(8));
	}
	
//	@Test
//	public void deberia_devolver_todos_los_registros_de_la_pagina_2(){
//		// preparaci�n
//		List<Persona> listaRegistros = new ArrayList<Persona>();
//		listaRegistros.add(new Persona("1"));
//		listaRegistros.add(new Persona("2"));
//		listaRegistros.add(new Persona("3"));
//		listaRegistros.add(new Persona("4"));
//		listaRegistros.add(new Persona("5"));
//		listaRegistros.add(new Persona("6"));
//		listaRegistros.add(new Persona("7"));
//		listaRegistros.add(new Persona("8"));
//		List<Persona> listaRegistrosPagina2 = new ArrayList<Persona>();
//		listaRegistrosPagina2.add(new Persona("4"));
//		listaRegistrosPagina2.add(new Persona("5"));
//		listaRegistrosPagina2.add(new Persona("6"));
//		Mockito.when(_mockGestorPersistencia.contarRegistros()).thenReturn(8);
//		Mockito.when(_mockGestorPersistencia.buscarTodos(2,3)).thenReturn(listaRegistrosPagina2);		
//		
//		// ejecuci�n
//		ResultadoPaginado<Persona> resultado = _dao.buscarTodos(2,3);
//		
//		// verificaci�n
//		Assert.assertThat(resultado, IsNull.notNullValue());
//		Assert.assertThat(resultado.getElementos(), IsNull.notNullValue());
//		Assert.assertThat(resultado.getElementos().size(), IsEqual.equalTo(3));
//		Assert.assertThat(resultado.getElementos().get(0), IsEqual.equalTo(new Persona("4")));
//		Assert.assertThat(resultado.getElementos().get(1), IsEqual.equalTo(new Persona("5")));
//		Assert.assertThat(resultado.getElementos().get(2), IsEqual.equalTo(new Persona("6")));
//		Assert.assertThat(resultado.getNumeroTotalPaginas(), IsEqual.equalTo(3));
//		Assert.assertThat(resultado.getPaginaActual(), IsEqual.equalTo(2));
//		Assert.assertThat(resultado.getNumeroTotalRegistros(), IsEqual.equalTo(8));
//	}

//	@Test
//	public void deberia_devolver_resultado_vacio_si_busca_paginada_pero_no_hay_registros(){
//		// preparaci�n
//		List<Persona> listaRegistros = new ArrayList<Persona>();
//		listaRegistros.add(new Persona("1"));
//		listaRegistros.add(new Persona("2"));
//		listaRegistros.add(new Persona("3"));
//		listaRegistros.add(new Persona("4"));
//		listaRegistros.add(new Persona("5"));
//		listaRegistros.add(new Persona("6"));
//		listaRegistros.add(new Persona("7"));
//		listaRegistros.add(new Persona("8"));
//		List<Persona> listaRegistrosPagina2 = new ArrayList<Persona>();
//		Mockito.when(_mockGestorPersistencia.contarRegistros()).thenReturn(0);
//		Mockito.when(_mockGestorPersistencia.buscarTodos(2,3)).thenReturn(listaRegistrosPagina2);		
//		
//		// ejecuci�n
//		ResultadoPaginado<Persona> resultado = _dao.buscarTodos(2,3);
//		
//		// verificaci�n
//		Assert.assertThat(resultado, IsNull.notNullValue());
//		Assert.assertThat(resultado.getElementos(), IsNull.notNullValue());
//		Assert.assertThat(resultado.getElementos().size(), IsEqual.equalTo(0));
//		Assert.assertThat(resultado.getNumeroTotalPaginas(), IsEqual.equalTo(0));
//		Assert.assertThat(resultado.getPaginaActual(), IsEqual.equalTo(2));
//		Assert.assertThat(resultado.getNumeroTotalRegistros(), IsEqual.equalTo(0));
//	}
	
//	@Test
//	public void deberia_lanzar_excepcion_si_busca_todos_los_registros_paginados_y_la_pagina_actual_es_0_o_negativa(){
//		// ejecuci�n
//		try{
//			ResultadoPaginado<Persona> resultado = _dao.buscarTodos(0,3);
//			Assert.fail("Deber�a lanzar excepci�n");
//		}
//		catch(DaoException e){}
//		
//		try{
//			ResultadoPaginado<Persona> resultado = _dao.buscarTodos(-5,3);
//			Assert.fail("Deber�a lanzar excepci�n");
//		}
//		catch(DaoException e){}
//		
//		
//		Assert.assertTrue(true);
//	}
//	
//	@Test
//	public void deberia_lanzar_excepcion_si_busca_todos_los_registros_paginados_y_el_numero_de_registros_por_pagina_es_0_o_negativo(){
//		// ejecuci�n
//		try{
//			ResultadoPaginado<Persona> resultado = _dao.buscarTodos(3,0);
//			Assert.fail("Deber�a lanzar excepci�n");
//		}
//		catch(DaoException e){}
//		
//		try{
//			ResultadoPaginado<Persona> resultado = _dao.buscarTodos(3,-3);
//			Assert.fail("Deber�a lanzar excepci�n");
//		}
//		catch(DaoException e){}
//				
//		Assert.assertTrue(true);
//	}
	
	@Test(expected=DaoException.class)
	public void deberia_lanzar_excepcion_si_busca_todos_los_registros_y_hay_problemas_al_realizar_consulta(){
		// preparaci�n
		Mockito.when(_mockGestorPersistencia.buscarTodos(Persona.class)).thenThrow(new RuntimeException());
		
		// ejecuci�n
		_dao.buscarTodos();		
	}
	
//	@Test(expected=DaoException.class)
//	public void deberia_lanzar_excepcion_si_busca_todos_los_registros_paginados_y_hay_problemas_al_realizar_consulta(){
//		// preparaci�n
//		Mockito.when(_mockGestorPersistencia.contarRegistros()).thenReturn(3);
//		Mockito.when(_mockGestorPersistencia.buscarTodos(2, 3)).thenThrow(new RuntimeException());
//		
//		// ejecuci�n
//		_dao.buscarTodos(2,3);		
//	}
	
//	@Test
//	public void deberia_paginar_registros(){
//		// preparaci�n
//		List<Persona> datos = new ArrayList<Persona>();
//		datos.add(new Persona("Isma"));
//		datos.add(new Persona("Bea"));
//		int paginaActual = 1;
//		int numRegPP = 2;
//		IPaginador<Persona> pagina = new Pagina<Persona>(paginaActual , numRegPP);
//		Pagina<Persona> paginaResultado = new Pagina<Persona>(paginaActual, numRegPP);
//		paginaResultado.setDatos(datos);
//		paginaResultado.setNumeroTotalRegistros(9);
//		Mockito.when(_mockGestorPersistencia.paginar(pagina, Persona.class)).thenReturn(paginaResultado);
//		
//		// ejecuci�n
//		pagina = _dao.paginar(pagina);		
//		
//		// verificaci�n
//		Assert.assertThat(pagina.getDatos(), IsEqual.equalTo(datos));
//		Assert.assertThat(pagina.getNumeroRegistrosPorPagina(), IsEqual.equalTo(numRegPP));
//		Assert.assertThat(pagina.getPagina(), IsEqual.equalTo(paginaActual));
//		Assert.assertThat(pagina.getNumeroTotalPaginas(), IsEqual.equalTo(5));
//		Assert.assertThat(pagina.getNumeroTotalRegistros(), IsEqual.equalTo(9));
//	}
}
