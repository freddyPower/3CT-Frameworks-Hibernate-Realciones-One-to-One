package com.tresct.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	
	//Atributo de sesion 
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	//Metodo para crear la sesion 
	
	private static SessionFactory buildSessionFactory() {				
		try {
			
			StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata metada = new MetadataSources( serviceRegistry ).getMetadataBuilder().build();
			return metada.getSessionFactoryBuilder().build();
			
		}catch( Throwable ex ){
			System.out.println("Error en session hibernate: " + ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}  //Finaliza buildSessionFactory
	
	//Obtener session
	public static SessionFactory getSessionFactory() {
		
		return sessionFactory;
	}

}
