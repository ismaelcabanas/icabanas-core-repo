package org.icabanas.jee.api.integracion.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.jee.api.integracion.dao.IGestorPersistencia;
import org.icabanas.jee.api.integracion.dao.IPaginador;
import org.icabanas.jee.api.integracion.dao.Pagina;
import org.icabanas.jee.api.integracion.entidad.Persona;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value=GestorPersistenciaJPA.class)
public class GestorPersistenciaJPATest {

	@Mock
	private EntityManager _mockEm;
	
	@Test(expected=IllegalArgumentException.class)
	public void deberia_lanzar_excepcion_si_crea_entidad_nula(){
		// preparación
		IGestorPersistencia gp = new GestorPersistenciaJPA(); 
		
		// ejecución
		gp.crear(null);
		
		// verificación		
	}
	
	@Test
	public void deberia_persistir_una_entidad(){
		// preparación
		IGestorPersistencia gp = new GestorPersistenciaJPA(_mockEm); 		
		Persona entidad = new Persona("Ismael");
		
		// ejecución
		gp.crear(entidad);
		
		// verificación	
		Mockito.verify(_mockEm, VerificationModeFactory.only()).persist(entidad);
		Mockito.verify(_mockEm, VerificationModeFactory.times(1)).persist(entidad);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deberia_lanzar_excepcion_si_modifica_entidad_nula(){
		// preparación
		IGestorPersistencia gp = new GestorPersistenciaJPA(); 
		
		// ejecución
		gp.modificar(null);
		
		// verificación		
	}
	
	@Test
	public void deberia_modificar_una_entidad(){
		// preparación
		IGestorPersistencia gp = new GestorPersistenciaJPA(_mockEm); 		
		Persona entidad = new Persona("Ismael");
		entidad.setId(1L);
		entidad.setNombre("Bea");
		
		Persona nuevaEntidad = new Persona();
		nuevaEntidad.setId(1L);
		nuevaEntidad.setNombre("Bea");
		Mockito.when(_mockEm.merge(entidad)).thenReturn(nuevaEntidad);
		
		// ejecución
		Persona testEntidad = gp.modificar(entidad);
		
		// verificación	
		Mockito.verify(_mockEm, VerificationModeFactory.only()).merge(entidad);
		Mockito.verify(_mockEm, VerificationModeFactory.times(1)).merge(entidad);
		Assert.assertThat(testEntidad, IsNull.notNullValue());
		Assert.assertThat(testEntidad.getNombre(), IsEqual.equalTo("Bea"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deberia_lanzar_excepcion_si_elimina_entidad_nula(){
		// preparación
		IGestorPersistencia gp = new GestorPersistenciaJPA(); 
		
		// ejecución
		gp.eliminar(null);
		
		// verificación		
	}
	
	@Test
	public void deberia_eliminar_una_entidad(){
		// preparación
		IGestorPersistencia gp = new GestorPersistenciaJPA(_mockEm); 		
		Persona entidad = new Persona("Ismael");
		entidad.setId(1L);
				
		// ejecución
		gp.eliminar(entidad);
		
		// verificación	
		Mockito.verify(_mockEm, VerificationModeFactory.only()).remove(entidad);
		Mockito.verify(_mockEm, VerificationModeFactory.times(1)).remove(entidad);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deberia_lanzar_excepcion_si_busca_entidad_por_id_nulo(){
		// preparación
		IGestorPersistencia gp = new GestorPersistenciaJPA(); 
		
		// ejecución
		gp.buscarPorId(null, Persona.class);
		
		// verificación		
	}
	
	@Test
	public void deberia_buscar_una_entidad_por_id(){
		// preparación
		IGestorPersistencia gp = new GestorPersistenciaJPA(_mockEm); 		
		Long id = 1L;
		Persona entidad = new Persona("Ismael");
		entidad.setId(id);
		Mockito.when(_mockEm.find(Persona.class, id)).thenReturn(entidad);
		
		// ejecución
		Persona testEntidad = gp.buscarPorId(id, Persona.class);
		
		// verificación	
		Mockito.verify(_mockEm, VerificationModeFactory.only()).find(Persona.class, id);
		Mockito.verify(_mockEm, VerificationModeFactory.times(1)).find(Persona.class, id);
		Assert.assertThat(testEntidad, IsNull.notNullValue());
		Assert.assertThat(testEntidad.getNombre(), IsEqual.equalTo("Ismael"));
		Assert.assertThat(testEntidad.getId(), IsEqual.equalTo(id));
	}
	
	@Test
	public void deberia_devolver_null_si_busca_una_entidad_por_id_y_no_existe(){
		// preparación
		IGestorPersistencia gp = new GestorPersistenciaJPA(_mockEm); 		
		Long id = 1L;
		Mockito.when(_mockEm.find(Persona.class, id)).thenThrow(EntityNotFoundException.class);
		
		// ejecución
		Persona testEntidad = gp.buscarPorId(id, Persona.class);
		
		// verificación	
		Mockito.verify(_mockEm, VerificationModeFactory.only()).find(Persona.class, id);
		Mockito.verify(_mockEm, VerificationModeFactory.times(1)).find(Persona.class, id);
		Assert.assertThat(testEntidad, IsNull.nullValue());
	}
	
	@Test
	public void deberia_devolver_todos_los_registros_de_una_entidad_persistente(){
		// preparación
		IGestorPersistencia gp = new GestorPersistenciaJPA(_mockEm);
		CriteriaBuilder _mockCb = Mockito.mock(CriteriaBuilder.class);
		CriteriaQuery<Persona> _mockCriteria = Mockito.mock(CriteriaQuery.class);
		TypedQuery<Persona> _mockQuery = Mockito.mock(TypedQuery.class);
		Mockito.when(_mockEm.getCriteriaBuilder()).thenReturn(_mockCb);
		Mockito.when(_mockCb.createQuery(Persona.class)).thenReturn(_mockCriteria);
		Mockito.when(_mockEm.createQuery(_mockCriteria)).thenReturn(_mockQuery);
		List<Persona> listaEntidades = new ArrayList<Persona>();
		listaEntidades.add(new Persona("Isma"));
		listaEntidades.add(new Persona("Bea"));
		listaEntidades.add(new Persona("Davicin"));
		Mockito.when(_mockQuery.getResultList()).thenReturn(listaEntidades);
		
		// ejecución
		List<Persona> testEntidades = gp.buscarTodos(Persona.class);
		
		// verificación	
		Mockito.verify(_mockEm, VerificationModeFactory.times(1)).getCriteriaBuilder();
		Mockito.verify(_mockEm, VerificationModeFactory.times(1)).createQuery(_mockCriteria);
		Assert.assertThat(testEntidades, IsNull.notNullValue());
		Assert.assertThat(testEntidades.size(), IsEqual.equalTo(3));
	}
	
	@Test
	public void deberia_devolver_listado_vacio_si_busca_todos_los_registros_de_una_entidad_persistente_y_no_existen_datos(){
		// preparación
		IGestorPersistencia gp = new GestorPersistenciaJPA(_mockEm);
		CriteriaBuilder _mockCb = Mockito.mock(CriteriaBuilder.class);
		CriteriaQuery<Persona> _mockCriteria = Mockito.mock(CriteriaQuery.class);
		TypedQuery<Persona> _mockQuery = Mockito.mock(TypedQuery.class);
		Mockito.when(_mockEm.getCriteriaBuilder()).thenReturn(_mockCb);
		Mockito.when(_mockCb.createQuery(Persona.class)).thenReturn(_mockCriteria);
		Mockito.when(_mockEm.createQuery(_mockCriteria)).thenReturn(_mockQuery);
		List<Persona> listaEntidades = new ArrayList<Persona>();
		Mockito.when(_mockQuery.getResultList()).thenReturn(listaEntidades);
		
		// ejecución
		List<Persona> testEntidades = gp.buscarTodos(Persona.class);
		
		// verificación	
		Mockito.verify(_mockEm, VerificationModeFactory.times(1)).getCriteriaBuilder();
		Mockito.verify(_mockEm, VerificationModeFactory.times(1)).createQuery(_mockCriteria);
		Assert.assertThat(testEntidades, IsNull.notNullValue());
		Assert.assertThat(testEntidades.size(), IsEqual.equalTo(0));
	}
	
//	@Test
//	public void deberia_devolver_los_registros_de_una_entidad_de_la_pagina_2_de_un_total_de_8_registros() throws Exception{
//		// preparación
//		GestorPersistenciaJPA _mockGp = PowerMockito.spy(new GestorPersistenciaJPA());
//		TypedQuery<Persona> _mockQuery = PowerMockito.mock(TypedQuery.class);
//		CriteriaQuery<Long> _mockCriteriaCount = PowerMockito.mock(CriteriaQuery.class);
//		int paginaActual = 2;
//		int numRegPP = 3;	
//		int numTotalReg = 8;
//		IPaginador<Persona> pagina = new Pagina<Persona>(paginaActual, numRegPP);
//		List<Persona> listaEntidades = new ArrayList<Persona>();
//		listaEntidades.add(new Persona("Isma"));
//		listaEntidades.add(new Persona("Bea"));
//		listaEntidades.add(new Persona("Davicin"));
//		PowerMockito.doReturn(_mockQuery).when(_mockGp, "getQuery", pagina, Persona.class);
////		PowerMockito.doReturn(_mockCriteriaCount).when(_mockGp, "getCriteriaCount", pagina, Persona.class);
//		PowerMockito.when(_mockQuery.getResultList()).thenReturn(listaEntidades);
////		PowerMockito.when(_mockEm.createQuery(_mockCriteriaCount).getSingleResult().intValue()).thenReturn(numTotalReg);
//		
//		// ejecución
//		IPaginador<Persona> paginaTest = _mockGp.paginar(pagina, Persona.class);
//		
//		// verificación
//		PowerMockito.verifyPrivate(_mockGp, VerificationModeFactory.times(1)).invoke("getQuery", pagina, Persona.class);
//		Assert.assertThat(paginaTest.getDatos(), IsNull.notNullValue());
//		Assert.assertThat(paginaTest.getDatos().size(), IsEqual.equalTo(3));
//		Assert.assertThat(paginaTest.getNumeroRegistrosPorPagina(), IsEqual.equalTo(3));
////		Assert.assertThat(paginaTest.getNumeroTotalRegistros(), IsEqual.equalTo(8));
////		Assert.assertThat(paginaTest.getNumeroTotalPaginas(), IsEqual.equalTo(3));
//	}	
	
//	@Test
//	public void deberia_devolver_los_registros_ordenados_por_un_campo(){
//		// preparación
//		IGestorPersistencia gp = new GestorPersistenciaJPA(_mockEm);
//		IFiltro filtro = new FiltroPorDefectoImpl();
//		filtro.setOrden(new Order("nombre",OrderEnum.ASC));
//		List<Persona> listaEntidades = new ArrayList<Persona>();
//		listaEntidades.add(new Persona("Bea"));			
//		listaEntidades.add(new Persona("Davicin"));
//		listaEntidades.add(new Persona("Isma"));
//				
//		// ejecución
//		List<Persona> testEntidades = gp.buscar(filtro, Persona.class);
//		
//		// verificación	
//		Assert.assertThat(testEntidades, IsNull.notNullValue());
//		Assert.assertThat(testEntidades, IsEqual.equalTo(listaEntidades));
//	}	
}
