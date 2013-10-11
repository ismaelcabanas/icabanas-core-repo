package org.icabanas.jee.api.integracion.dao.jpa.it;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;


public abstract class AbstractTestIT {
	private EntityManagerFactory entityManagerFactory;
    protected final TestJPAUtil jpaUtil = new TestJPAUtil();
    private EntityManager entityManager;

    protected void generarDatos() {
    	TestJPAUtil.generarDatos(entityManagerFactory);
    }

    @Before
    public void crearPersistenceUnit() {
        if (entityManagerFactory == null) {        	
            entityManagerFactory = TestJPAUtil.crearEntityManagerFactoryParaHibernate();
//            entityManagerFactory = JPAUtil.crearEntityManagerFactoryParaEclipseLink();
//            entityManagerFactory = JPAUtil.crearEntityManagerFactoryParaOpenJPA();            
        }                
        
        //generarDatos();
        entityManager = entityManagerFactory.createEntityManager();
        
        jpaUtil.iniciarConexion(entityManager);
    }
    
    @After
    public void cerrarPersistenceUnit() {
    	jpaUtil.cerrarConexion();
        entityManagerFactory.close();
    }
  
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
