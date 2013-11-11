package org.icabanas.jee.api.integracion.dao.jpa.it;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;


public abstract class AbstractTestIT {
	private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
	private String persistenceUnitName;

	
    
    public AbstractTestIT() {
	}

	public AbstractTestIT(String persistenceUnitName) {
		this.persistenceUnitName = persistenceUnitName;
	}
	
    protected abstract void generarDatos();

    @Before
    public void crearPersistenceUnit() {
        if (entityManagerFactory == null) {        	
            entityManagerFactory = crearEntityManagerFactoryParaHibernate();
//            entityManagerFactory = JPAUtil.crearEntityManagerFactoryParaEclipseLink();
//            entityManagerFactory = JPAUtil.crearEntityManagerFactoryParaOpenJPA();            
        }                
        
        //generarDatos();
        entityManager = entityManagerFactory.createEntityManager();
        
        iniciarConexion(entityManager);
    }
    
    @After
    public void cerrarPersistenceUnit() {
    	cerrarConexion();
        entityManagerFactory.close();
    }
  
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
    protected EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
	private EntityManagerFactory crearEntityManagerFactoryParaHibernate() {
		return Persistence.createEntityManagerFactory(persistenceUnitName);
	}
    
	private void iniciarConexion(EntityManager em) {
		this.entityManager = em;
		this.entityManager.getTransaction().begin();		
	}

	private void cerrarConexion() {
		this.entityManager.getTransaction().commit();
		this.entityManager.close();
	}
	
}
