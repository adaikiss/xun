package org.adaikiss.xun.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
	private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
	
	private static final SessionFactory sessionFactory;

	static {
		try {
			Configuration configuration = new Configuration(); 
		    configuration.configure(); 
		    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();         
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
//			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
//					.configure().buildServiceRegistry();
//			// Create the SessionFactory from hibernate.cfg.xml
//			sessionFactory = new Configuration().configure()
//					.buildSessionFactory();
		} catch (Exception ex) {
			// Make sure you log the exception, as it might be swallowed
			logger.error("Initial SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}