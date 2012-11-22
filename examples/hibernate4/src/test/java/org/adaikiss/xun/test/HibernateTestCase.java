/**
 * 
 */
package org.adaikiss.xun.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;

/**
 * @author hlw
 *
 */
public class HibernateTestCase {

	protected SessionFactory sessionFactory;

	@Before
	public void setUp() throws Exception{
		Configuration configuration = new Configuration(); 
	    configuration.configure(); 
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();         
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
	}

	@After
	public void tearDown() throws Exception{
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}
}
