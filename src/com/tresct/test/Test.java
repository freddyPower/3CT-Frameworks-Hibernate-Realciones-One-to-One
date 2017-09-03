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

public class Test {

	public static void main(String[] args) {
		
//		Avaluo a = new Avaluo("Itzacalco");
//		insertaAvaluo(a);
		
		consultaOneToOne();
	}
	
	
	//Metodo para insertar un avaluo
	public static  void insertaAvaluo( Avaluo a) {
		
		Session sesion = null;
		try {
			
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			
			Tramite t = sesion.load(Tramite.class, 7);			
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
	

}
