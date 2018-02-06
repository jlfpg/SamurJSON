package accesoDatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import auxiliares.HibernateUtil;
import modelo.Instalacion;

public class AccesoHibernate implements Datos {

	Session session;

	public AccesoHibernate() {

		HibernateUtil util = new HibernateUtil();
		session = util.getSessionFactory().openSession();
	}

	public void cargarTodo() {
		obtenerInstalacion();
	}

	@Override
	public HashMap<Integer, Instalacion> obtenerInstalacion() {
		HashMap<Integer, Instalacion> instalaciones = new HashMap<Integer, Instalacion>();
		List<Instalacion> datos = new ArrayList<Instalacion>();
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			datos = session.createQuery("from instalacion").list();
			for (Instalacion i : datos)
				instalaciones.put(i.getCodparque(), i);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return instalaciones;
	}

	@Override
	public boolean guardarInstalacion(HashMap<Integer, Instalacion> instalacion) {
		Instalacion in = null;
		in = instalacion.get(1);
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			session.save(in);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return true;
	}

	@Override
	public boolean updateInstalacion(HashMap<Integer, Instalacion> instalacion) {
		Instalacion in = null;
		in = instalacion.get(1);
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			session.saveOrUpdate(in);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return true;
	}

	@Override
	public boolean deleteInstalacion(HashMap<Integer, Instalacion> instalacion) {
		Instalacion ar = null;
		ar = instalacion.get(1);
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			Instalacion ins = session.load(Instalacion.class, ar.getCodparque());
			session.delete(ins);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return true;
	}

	public void borrarLugarTodo() {
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			Query q = session.createQuery("delete from instalacion");
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	@Override
	public HashMap<Integer, Instalacion> obtenerInstalacionM() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardarInstalacionM(Instalacion instalacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarInstalacionM(Instalacion instalacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarInstalacionM(Instalacion instalacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarTodo() {
		// TODO Auto-generated method stub
		
	}

	
}
