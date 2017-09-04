package com.tresct.test;

import java.util.List;

import javax.management.OperationsException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;


import com.tresct.dto.Avaluo;
import com.tresct.dto.Tramite;
import com.tresct.util.HibernateUtil;

//Esta clase demeustra como realizar operaciones crud en relaciones one to one
public class Test {

	public static void main(String[] args) {
		
		//Avaluo a = new Avaluo("Itzacalco");
		//insertaAvaluo(a);
		//actualizaAvaluo();
		//consultaOneToOne();
		try {
			
			eliminaAvaluo();
			
		}catch( Exception hbe ) {
			
			System.out.println("HibernateeXCEPTION: " + hbe.getMessage());
			
		}
		
		//eliminaTramiteCascada();
		
	}
	
	
	//Metodo para insertar un avaluo
	public static  void insertaAvaluo( Avaluo a) {
		
		Session sesion = null;
		try {
			
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			
			Tramite t = sesion.load(Tramite.class, 2);			
			System.out.println("El tramite cargado es: " + t.toString());
			a.setTramite(t);
			sesion.save(a);
			sesion.getTransaction().commit();
			
		}catch(HibernateException  hbe) {			
			System.out.println("HibernateException : " +  hbe.getMessage());
			sesion.getTransaction().rollback();
			sesion.close();			
		}finally {
			sesion.close();			
		}
		
	}
	
	//Consulta one to one
	public static String  consultaOneToOne() {
		
		List<Avaluo> listaAvaluos = null;
		Session sesion = null;
				
		try {
		
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			
			//Consulta del tramite de un avaluo 
			Avaluo a = sesion.load(Avaluo.class, 2);
			Tramite t = a.getTramite();
			
			System.out.println("Tramite del avaluo 2: " + t.toString());			
			
			//Consulta de todos los tramites que aparecen en avaluos
			CriteriaBuilder builder = sesion.getCriteriaBuilder();
			CriteriaQuery <Avaluo> criteria = builder.createQuery(Avaluo.class);
			Root<Avaluo> root = criteria.from(Avaluo.class);
			
			criteria.select(root);
			
			listaAvaluos = sesion.createQuery(criteria).getResultList();
			System.out.println("ListaAvaluos: "  + listaAvaluos.toString());
			
			
		}catch( HibernateException hbe) {			
			System.out.println("Excepcion en consultaOneToOne: " + hbe.getMessage());
			sesion.getTransaction().rollback();
			sesion.close();
		}finally {
			sesion.close();		
		}			
		return listaAvaluos.toString();		
	}
	
	
	public static void actualizaAvaluo() {
		
		Session sesion =  null;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			
			Avaluo a = sesion.load(Avaluo.class, 2);
			a.setLugarAvaluo("2da cda de joya de nieves CDMX 2");
			sesion.update(a);
			sesion.getTransaction().commit();
						
		}catch( HibernateException hbe ) {
			System.out.println("HibernateException : " + hbe.getMessage());
			sesion.getTransaction().rollback();
			sesion.close();			
		}finally {
			sesion.close();			
		}	
	}
	
	public static void eliminaAvaluo() {
		Session sesion = null;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			
			Avaluo a = sesion.load(Avaluo.class, 3);
			sesion.delete(a);
			sesion.getTransaction().commit();
			
		}catch( HibernateException hbe ) {
			System.out.println("HibernateException: " + hbe.getMessage());
			sesion.getTransaction().rollback();
			sesion.close();
			
		}finally {
			sesion.close();			
		}
	}
	
	//Eliminacion en cascada
	public static void eliminaTramiteCascada() {
		Session sesion = null;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Tramite t = sesion.load(Tramite.class, 2);
			
			sesion.delete(t);
			sesion.getTransaction().commit();
			
		}catch( HibernateException hbe ) {
			System.out.println("HibernateException: " + hbe.getMessage());
			sesion.getTransaction().rollback();
			sesion.close();
			
		}finally {
			sesion.close();			
		}
	}
	
}
