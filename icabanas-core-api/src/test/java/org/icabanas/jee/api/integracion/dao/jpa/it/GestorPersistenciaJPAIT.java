package org.icabanas.jee.api.integracion.dao.jpa.it;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.jee.api.integracion.dao.jpa.GestorPersistenciaJPA;
import org.icabanas.jee.api.integracion.entidad.Persona;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GestorPersistenciaJPAIT extends AbstractTestIT {

	
	private GestorPersistenciaJPA gp;
	
	
	public GestorPersistenciaJPAIT() {
		super("HibernateJPATest");
	}

	@Before
	public void configura_test(){
		gp = new GestorPersistenciaJPA(getEntityManager());
		
		_altaPersonas();
	}
	
	@Test
	public void deberia_persistir_entidad(){
		// preparaci�n
		Persona ismael = new Persona("Ismael");
		
		// ejecuci�n
		gp.crear(ismael);
		
		// verificaci�n
		assertThat(ismael.getId(), notNullValue());
		Persona copia = gp.buscarPorId(ismael.getId(), Persona.class);
		assertThat(ismael,equalTo(copia));
	}	
	
	@Test
	public void deberia_actualizar_entidad(){
		// preparaci�n
		Persona ismael = new Persona("Ismael");
		gp.crear(ismael);
		Persona ismaelPersistido = new Persona();
		ismaelPersistido.setId(ismael.getId());
		ismaelPersistido.setNombre(ismael.getNombre());
		ismaelPersistido.setNif("51942403P");
		
		// ejecuci�n
		Persona test = gp.modificar(ismaelPersistido);
		
		// verificaci�n
		Persona copia = gp.buscarPorId(test.getId(), Persona.class);
		assertThat(test, equalTo(copia));
	}
	
	@Test
	public void deberia_eliminar_entidad(){
		// preparaci�n
		Persona ismael = new Persona("Ismael");
		gp.crear(ismael);
		
		// ejecuci�n
		gp.eliminar(ismael);
		
		// verificaci�n
		Persona test = gp.buscarPorId(ismael.getId(), Persona.class);
		assertThat(test, IsNull.nullValue());
	}
	
	@Test
	public void deberia_buscar_entidad_por_id_existente(){
		// preparaci�n
		Persona ismael = new Persona("Ismael");
		gp.crear(ismael);
		
		// ejecuci�n
		Persona test = gp.buscarPorId(ismael.getId(), Persona.class);
		
		// verificaci�n
		assertThat(test, notNullValue());
		assertThat(test.getNombre(), equalTo("Ismael"));
	}
	
	@Test
	public void deberia_devolver_null_si_busca_entidad_por_id_inexistente(){
		// preparaci�n
		Long id = 99L;
		
		// ejecuci�n
		Persona test = gp.buscarPorId(id, Persona.class);
		
		// verificaci�n
		assertThat(test, IsNull.nullValue());		
	}
	
	@Test
	public void deberia_buscar_todos(){				
		// ejecuci�n
		List<Persona> personas = gp.buscarTodos(Persona.class);
		
		// verificaci�n
		Assert.assertThat(personas, IsNull.notNullValue());
		Assert.assertThat(personas.size(), IsEqual.equalTo(5));
	}
	
//	@Test
//	public void deberia_realizar_busqueda_paginada_de_todos_los_registros(){
//		// preparaci�n
//		int paginaActual = 1;
//		int numRegPP = 2;
//		IPaginador<Persona> pagina = new Pagina<Persona>(paginaActual, numRegPP);
//		
//		// ejecuci�n
//		IPaginador<Persona> paginaTest = gp.paginar(pagina, Persona.class);
//		
//		// verificaci�n
//		Assert.assertThat(paginaTest, IsNull.notNullValue());
//		Assert.assertThat(paginaTest.getPagina(), IsEqual.equalTo(1));
//		Assert.assertThat(paginaTest.getPrimerRegistro()+1, IsEqual.equalTo(1));
//		Assert.assertThat(paginaTest.getNumeroRegistrosPorPagina(), IsEqual.equalTo(2));
//		Assert.assertThat(paginaTest.getNumeroTotalRegistros(), IsEqual.equalTo(5));
//		Assert.assertThat(paginaTest.getNumeroTotalPaginas(), IsEqual.equalTo(3));
//	}
	
	private void _altaPersonas() {		
		gp.crear(new Persona("Ismael",37));
		gp.crear(new Persona("Beatriz"));
		gp.crear(new Persona("Antonio"));
		gp.crear(new Persona("Yolanda"));
		gp.crear(new Persona("David"));
	}

	@Override
	protected void generarDatos() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		
		em.getTransaction().begin();
		
		Persona p1 = new Persona("p1");
		em.persist(p1);
		
		em.getTransaction().commit();
		
		em.close();
		getEntityManagerFactory().close();		
	}
}
