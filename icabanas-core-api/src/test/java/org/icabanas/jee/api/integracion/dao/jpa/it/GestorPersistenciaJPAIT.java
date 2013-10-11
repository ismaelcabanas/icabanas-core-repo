package org.icabanas.jee.api.integracion.dao.jpa.it;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.icabanas.jee.api.integracion.dao.IPaginador;
import org.icabanas.jee.api.integracion.dao.Pagina;
import org.icabanas.jee.api.integracion.dao.jpa.GestorPersistenciaJPA;
import org.icabanas.jee.api.integracion.entidad.Persona;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GestorPersistenciaJPAIT extends AbstractTestIT {

	private GestorPersistenciaJPA gp;
	
	@Before
	public void configura_test(){
		gp = new GestorPersistenciaJPA(getEntityManager());
		
		_altaPersonas();
	}
	
	@Test
	public void deberia_persistir_entidad(){
		// preparación
		Persona ismael = new Persona("Ismael");
		
		// ejecución
		gp.crear(ismael);
		
		// verificación
		assertThat(ismael,notNullValue());
		assertThat(ismael.getId(), notNullValue());
		assertThat(ismael.getNombre(),equalTo("Ismael"));
	}	
	
	@Test
	public void deberia_actualizar_entidad(){
		// preparación
		Persona ismael = new Persona("Ismael");
		gp.crear(ismael);
		Persona ismaelPersistido = new Persona();
		ismaelPersistido.setId(ismael.getId());
		ismaelPersistido.setNombre(ismael.getNombre());
		ismaelPersistido.setNif("51942403P");
		
		// ejecución
		Persona test = gp.modificar(ismaelPersistido);
		
		// verificación
		assertThat(test.getId(), notNullValue());
		assertThat(test.getNombre(),equalTo("Ismael"));
		assertThat(test.getNif(),equalTo("51942403P"));
	}
	
	@Test
	public void deberia_eliminar_entidad(){
		// preparación
		Persona ismael = new Persona("Ismael");
		gp.crear(ismael);
		
		// ejecución
		gp.eliminar(ismael);
		
		// verificación
		Persona test = gp.buscarPorId(ismael.getId(), Persona.class);
		assertThat(test, IsNull.nullValue());
	}
	
	@Test
	public void deberia_buscar_entidad_por_id_existente(){
		// preparación
		Persona ismael = new Persona("Ismael");
		gp.crear(ismael);
		
		// ejecución
		Persona test = gp.buscarPorId(ismael.getId(), Persona.class);
		
		// verificación
		assertThat(test, notNullValue());
		assertThat(test.getNombre(), equalTo("Ismael"));
	}
	
	@Test
	public void deberia_devolver_null_si_busca_entidad_por_id_inexistente(){
		// preparación
		Long id = 99L;
		
		// ejecución
		Persona test = gp.buscarPorId(id, Persona.class);
		
		// verificación
		assertThat(test, IsNull.nullValue());		
	}
	
	@Test
	public void deberia_buscar_todos(){				
		// ejecución
		List<Persona> personas = gp.buscarTodos(Persona.class);
		
		// verificación
		Assert.assertThat(personas, IsNull.notNullValue());
		Assert.assertThat(personas.size(), IsEqual.equalTo(5));
	}
	
//	@Test
//	public void deberia_realizar_busqueda_paginada_de_todos_los_registros(){
//		// preparación
//		int paginaActual = 1;
//		int numRegPP = 2;
//		IPaginador<Persona> pagina = new Pagina<Persona>(paginaActual, numRegPP);
//		
//		// ejecución
//		IPaginador<Persona> paginaTest = gp.paginar(pagina, Persona.class);
//		
//		// verificación
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
}
