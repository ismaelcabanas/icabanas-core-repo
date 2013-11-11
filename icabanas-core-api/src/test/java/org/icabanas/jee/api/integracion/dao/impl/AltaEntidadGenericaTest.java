package org.icabanas.jee.api.integracion.dao.impl;

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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class AltaEntidadGenericaTest {

	@Mock
	private IGestorPersistencia _mockGestorPersistencia;
	private IGenericDao<Long, Persona> dao;

	@Before
	public void configura_test(){
		dao = new GenericDao<Long, Persona>();
		dao.setGestorPersistencia(_mockGestorPersistencia);
	}
	
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
		dao.crear(entidad);
		
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
			dao.crear(entidad);
			Assert.fail("El test debería fallar ya que no se puede dar de alta una entidad nula.");
		}
		catch(DaoException e){
			Assert.assertThat(e.getMessage(), IsEqual.equalTo("java.lang.RuntimeException: El parámetro entidad no puede ser nulo."));
		}

		// VERIFICACIÓN
		Mockito.verify(_mockGestorPersistencia).crear(entidad);
	}
	
}
