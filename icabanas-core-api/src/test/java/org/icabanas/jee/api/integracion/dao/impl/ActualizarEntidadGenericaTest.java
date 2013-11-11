package org.icabanas.jee.api.integracion.dao.impl;

import static org.junit.Assert.*;

import org.hamcrest.core.IsEqual;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.icabanas.jee.api.integracion.dao.IGestorPersistencia;
import org.icabanas.jee.api.integracion.entidad.Persona;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActualizarEntidadGenericaTest {

	@Mock
	private IGestorPersistencia _mockGestorPersistencia;
	private IGenericDao<Long, Persona> dao;

	@Before
	public void configura_test(){
		dao = new GenericDao<Long, Persona>();
		dao.setGestorPersistencia(_mockGestorPersistencia);
	}
	
	@Test
	public void deberia_actualizar_entidad() throws Exception {
		// PREPARACIÓN
		Persona entidadEsperada = new Persona("Ismael");
		entidadEsperada.setId(1L);
		Persona entidad = new Persona("Pepe");
		entidad.setNombre("Ismael");
		entidad.setId(1L);
		Mockito.when(_mockGestorPersistencia.modificar(entidad)).thenReturn(entidadEsperada);
		
		// EJECUCIÓN
		Persona entidadMod = dao.modificar(entidad);

		// VERIFICACIÓN
		Assert.assertThat(entidadMod, IsEqual.equalTo(entidadEsperada));
		Mockito.verify(_mockGestorPersistencia).modificar(entidad);
	}
}
