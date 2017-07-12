package com.BananaTechies.persistencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.BananaTechies.models.Proyecto;



public class ManageProyecto {

	private SessionFactory factory = null;

	private static ManageProyecto instance = null;

	private ManageProyecto() {
		factory = new Configuration().configure().buildSessionFactory();
	}

	public static ManageProyecto getInstance() {
		if (instance == null) {
			instance = new ManageProyecto();
		}

		return instance;
	}

	public void insertEntities() {
		Session session = factory.openSession();

		Transaction t = session.beginTransaction();

		Proyecto e1 = new Proyecto();
		session.persist(e1);

		Proyecto e2 = new Proyecto();
		session.persist(e2);



		t.commit();
		session.close();
	}

	public int insertProyecto(Proyecto proyectoNuevo) {

		Session session = factory.openSession();

		Transaction t = session.beginTransaction();
		int eid = ((Integer) session.save(proyectoNuevo)).intValue();

		t.commit();
		session.close();

		return eid;
	}
	
	public Proyecto getProyecto(int eid){
		Session session = factory.openSession();

		Transaction t = session.beginTransaction();
		Proyecto elProyecto = (Proyecto)session.get(Proyecto.class,eid);

		t.commit();
		session.close();

		return elProyecto;
	}
}
