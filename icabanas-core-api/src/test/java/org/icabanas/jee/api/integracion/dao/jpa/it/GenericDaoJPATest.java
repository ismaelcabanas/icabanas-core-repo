package org.icabanas.jee.api.integracion.dao.jpa.it;

import org.hamcrest.core.IsNull;
import org.icabanas.jee.api.integracion.dao.DaoException;
import org.icabanas.jee.api.integracion.dao.IGenericDao;
import org.icabanas.jee.api.integracion.entidad.Persona;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:META-INF/spring/test-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("default")
public class GenericDaoJPATest {
	
	@Autowired
	private IGenericDao<Long, Persona> personaDao;
	
	@Test
	public void deberia_crear_una_persona(){
		// preparación
		Persona persona = new Persona("Ismael", 38);
		
		// ejecución
		personaDao.crear(persona);
		
		// verificación
		Assert.assertThat(persona.getId(), IsNull.notNullValue());
	}
	
	@Test
	public void deberia_dar_error_al_crear_la_misma_persona(){
		// preparación
		Persona persona = new Persona("Ismael", 38);
		personaDao.crear(persona);
		
		// ejecución
		try{
			personaDao.crear(persona);
			Assert.fail("Debería lanzar excepción");
		}
		catch (DaoException e) {
			Assert.assertTrue(true);
		}
	}
}
