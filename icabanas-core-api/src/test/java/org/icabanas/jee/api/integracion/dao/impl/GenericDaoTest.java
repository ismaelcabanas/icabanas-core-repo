package org.icabanas.jee.api.integracion.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.jee.api.integracion.dao.DaoException;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.icabanas.jee.api.integracion.dao.IGestorPersistencia;
import org.icabanas.jee.api.integracion.entidad.Persona;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class GenericDaoTest {

	@Mock
	private IGestorPersistencia _mockGestorPersistencia;
	private IGenericDao<Long, Persona> _dao;
		
//	@Mock
//	private IProcesadorConsultas<Persona> _mockProcesadorConsultas;
	
	@Before
	public void configurarTest(){
		_dao = new GenericDao<Long, Persona>();
		_dao.setGestorPersistencia(_mockGestorPersistencia);
//		_dao = new PersonaDaoImpl();
////		Mockito.when(_mockGestorPersistencia.getProcesadorConsultas()).thenReturn(_mockProcesadorConsultas);
//		_dao.setGestorPersistencia(_mockGestorPersistencia);		
////		_dao.setProcesadorConsultas(_mockProcesadorConsultas);
	}
	
//	@Test
//	public void deberia_crear_instancia(){
//		// preparaci�n y ejecuci�n
//		GenericDao<Long, Persona> dao = new PersonaDaoImpl();
//		
//		//verificaci�n
//		Assert.assertThat(dao, IsNull.notNullValue());
//		Assert.assertThat(dao.getGestorPersistencia(), IsNull.nullValue());
//		Assert.assertThat(dao.getPersistentClass(), IsEqual.equalTo(Persona.class));
////		Assert.assertThat(dao.getProcesadorConsultas(), IsNull.nullValue());
//	}
//	
//	@Test
//	public void deberia_crear_instancia_con_gestor_persistencia(){
//		// preparaci�n y ejecuci�n
//		GenericDao<Long, Persona> dao = new PersonaDaoImpl();		
//		dao.setGestorPersistencia(_mockGestorPersistencia);		
////		dao.setProcesadorConsultas(_mockProcesadorConsultas);
////		Mockito.when(_mockGestorPersistencia.getProcesadorConsultas()).thenReturn(_mockProcesadorConsultas);
//		
//		//verificaci�n
//		Assert.assertThat(dao, IsNull.notNullValue());
//		Assert.assertThat(dao.getGestorPersistencia(), IsNull.notNullValue());
////		Assert.assertThat(dao.getProcesadorConsultas(), IsNull.notNullValue());
//		Assert.assertThat(dao.getPersistentClass(), IsEqual.equalTo(Persona.class));
//	}
	
	@Test
	public void deberia_crear_entidad(){
		// preparación
		Persona entidadEsperada = new Persona("Ismael");
		entidadEsperada.setId(1L);
		Persona entidad = new Persona("Ismael");
				
		Mockito.doAnswer(new Answer<Persona>() {
			@Override
			public Persona answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				Persona unaEntidad = (Persona) args[0];
				unaEntidad.setId(1L);
				return null;
			}			
		}).when(_mockGestorPersistencia).crear(entidad);
		
		// ejecución
		_dao.crear(entidad);
		
		//verificación
		Assert.assertThat(entidad, IsNull.notNullValue());
		Assert.assertThat(entidad.getId(), IsNull.notNullValue());
		Assert.assertThat(entidad.getNombre(), IsEqual.equalTo(entidadEsperada.getNombre()));
		Mockito.verify(_mockGestorPersistencia).crear(entidad);
	}
	
	@Test
	public void deberia_lanzar_excepcion_si_crea_una_entidad_nula() throws Exception {
		// PREPARACIÓN
		Persona entidad = null;
		Mockito.doThrow(new RuntimeException("El parámetro entidad no puede ser nulo.")).when(_mockGestorPersistencia).crear(entidad);
		
		// EJECUCIÓN
		try{
			_dao.crear(entidad);
			Assert.fail("El test debería fallar ya que no se puede dar de alta una entidad nula.");
		}
		catch(DaoException e){
			Assert.assertThat(e.getMessage(), IsEqual.equalTo("java.lang.RuntimeException: El parámetro entidad no puede ser nulo."));
		}

		// VERIFICACIÓN
		Mockito.verify(_mockGestorPersistencia).crear(entidad);
	}

	@Test
	public void deberia_actualizar_una_entidad(){
		// preparaci�n
		Persona unaEntidad = new Persona("isma");
		unaEntidad.setId(1L);
		Persona entidadModificada = new Persona("isma");
		entidadModificada.setId(1L);
		entidadModificada.setNombre("ismael");
		Mockito.when(_mockGestorPersistencia.modificar(unaEntidad)).thenReturn(entidadModificada);
		
		// ejecuci�n
		Persona test = _dao.modificar(unaEntidad);
		
		// verificaci�n		
		Assert.assertThat(test.getNombre(), IsEqual.equalTo("ismael"));
		Mockito.verify(_mockGestorPersistencia, VerificationModeFactory.times(1)).modificar(unaEntidad);
	}
	
	@Test
	public void deberia_lanzar_excepcion_si_entidad_a_actualizar_es_null(){
		// PREPARACIÓN
		Persona entidad = null;
		Mockito.doThrow(new RuntimeException("El parámetro entidad no puede ser nulo.")).when(_mockGestorPersistencia).modificar(entidad);
		
		// EJECUCIÓN
		try{
			_dao.modificar(entidad);
			Assert.fail("El test debería fallar ya que no se puede modificar una entidad nula.");
		}
		catch(DaoException e){
			Assert.assertThat(e.getMessage(), IsEqual.equalTo("java.lang.RuntimeException: El parámetro entidad no puede ser nulo."));
		}

		// VERIFICACIÓN
		Mockito.verify(_mockGestorPersistencia).modificar(entidad);
	}
	
	@Test(expected=DaoException.class)
	public void deberia_lanzar_excepcion_si_ha_problemas_al_modificar_entidad(){
		// preparaci�n
		Persona unaEntidad = new Persona("isma");
		Mockito.doThrow(RuntimeException.class).when(_mockGestorPersistencia).modificar(unaEntidad);
		
		// ejecuci�n
		_dao.modificar(unaEntidad);
		
		// verificaci�n		
	}
	
	@Test
	public void deberia_eliminar_una_entidad(){
		// preparaci�n
		Persona unaEntidad = new Persona("isma");
		unaEntidad.setId(1L);
		Mockito.when(_mockGestorPersistencia.buscarPorId(1L, Persona.class)).thenReturn(null);
				
		// ejecuci�n
		_dao.eliminar(unaEntidad);
		
		// verificaci�n		
		Persona entidadEliminada = _dao.buscarPorId(unaEntidad.getId());
		Assert.assertThat(entidadEliminada, IsNull.nullValue());
		Mockito.verify(_mockGestorPersistencia, VerificationModeFactory.times(1)).eliminar(unaEntidad);
	}
	
	@Test
	public void deberia_lanzar_excepcion_si_entidad_a_eliminar_es_null(){
		// preparaci�n
		Persona unaEntidad = null;
		Throwable excepcion = null;
		Mockito.doThrow(IllegalArgumentException.class).when(_mockGestorPersistencia).eliminar(unaEntidad);
		
		// ejecuci�n
		try{
			_dao.eliminar(unaEntidad);
		}
		catch(DaoException ex){
			excepcion  = ex.getCause();
		}
		
		// verificaci�n		
		Assert.assertThat(excepcion, Is.is(IllegalArgumentException.class));
	}
	
	@Test
	public void deberia_eliminar_todas_las_entidades(){	
		// preparaci�n
		List<Persona> resultado = new ArrayList<Persona>();
		Mockito.when(_mockGestorPersistencia.buscarTodos(Persona.class)).thenReturn(resultado);
				
		// ejecuci�n
		_dao.eliminarTodas();
		
		// verificaci�n
		Mockito.verify(_mockGestorPersistencia, VerificationModeFactory.times(1)).eliminarTodas();
		List<Persona> entidadesEliminadas = _dao.buscarTodos();
		Assert.assertThat(entidadesEliminadas.size(), IsEqual.equalTo(0));
	}
	
	@Test(expected=DaoException.class)
	public void deberia_lanzar_excepcion_si_hay_problemas_al_eliminar_las_entidades(){
		// preparaci�n
		Persona unaEntidad = new Persona("isma");
		Mockito.doThrow(RuntimeException.class).when(_mockGestorPersistencia).eliminar(unaEntidad);
		
		// ejecuci�n
		_dao.eliminar(unaEntidad);
		
		// verificaci�n		
	}
	
	@Test(expected=DaoException.class)
	public void deberia_lanzar_excepcion_si_hay_problemas_al_eliminar_todas_entidades(){
		// preparaci�n
		Mockito.doThrow(RuntimeException.class).when(_mockGestorPersistencia).eliminarTodas();
		
		// ejecuci�n
		_dao.eliminarTodas();
		
		// verificaci�n		
	}		
}
