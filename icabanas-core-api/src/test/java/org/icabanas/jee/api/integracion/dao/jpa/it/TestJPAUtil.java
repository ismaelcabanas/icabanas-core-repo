package org.icabanas.jee.api.integracion.dao.jpa.it;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.icabanas.jee.api.integracion.entidad.Persona;


public class TestJPAUtil {

	private EntityManager em;
	
	public static void generarDatos(EntityManagerFactory entityManagerFactory) {				
		generarPersonas(entityManagerFactory);
		
	}

	public static EntityManagerFactory crearEntityManagerFactoryParaHibernate() {
		return Persistence.createEntityManagerFactory("HibernateJPATest");
	}
	
	private static void generarPersonas(EntityManagerFactory entityManagerFactory) {
		EntityManager em = entityManagerFactory.createEntityManager();
		
		em.getTransaction().begin();
		
		Persona p1 = new Persona("p1");
		em.persist(p1);
		
		em.getTransaction().commit();
		
		em.close();
		entityManagerFactory.close();
	}	

	public void iniciarConexion(EntityManager em) {
		this.em = em;
		this.em.getTransaction().begin();		
	}

	public void cerrarConexion() {
		this.em.getTransaction().commit();
		this.em.close();
	}

	public EntityManager getEntityManager() {
		return this.em;
	}	
	
}
