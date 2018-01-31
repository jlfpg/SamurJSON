package accesoDatos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;

import auxiliares.HibernateUtil;
import modelo.Instalacion;

public class AccesoHibernate {

	Session session;

	public AccesoHibernate() {

		HibernateUtil util = new HibernateUtil();
		session = util.getSessionFactory().openSession();
	}

	public void cargarTodo() {
		obtenerLugar();
	}

	
	public HashMap<Integer, Instalacion> obtenerLugar() {
		Query q = session.createQuery("select dis from Lugar dis");
		List results = q.list();
		String clave;
		HashMap<Integer, Instalacion> dispensadorCreados = new HashMap<Integer, Instalacion>();
		Iterator equiposIterator = results.iterator();

		while (equiposIterator.hasNext()) {
			Instalacion team = (Instalacion) equiposIterator.next();
			// team = new Dispensador(team.getClave(), team.getNombreProducto(),
			// team.getPrecio(), team.getCantidad());
			// clave = team.getClave();
			dispensadorCreados.put(team.getCodparque(), team);
		}
		return dispensadorCreados;
	}

	public void guardarLugar(Instalacion ar) {
		session.beginTransaction();
		Query q = session.createQuery("select max(codParque) from Lugar");
		List results = q.list();
		int id =(int) results.iterator().next()+1;
		
		Instalacion lug= new Instalacion(id,ar.getNombre(),ar.getTelefono(),ar.getDireccion());
		HashMap<Integer, Instalacion> lugar = new HashMap<Integer, Instalacion>();
		lugar.put(id, lug);
		for (Entry<Integer, Instalacion> entry : lugar.entrySet()) {
			session.save(entry.getValue());
		}
		
		session.getTransaction().commit();
	}
	public void borrarLugar(String nombre) {
		session.beginTransaction();
        Query q = session.createQuery("delete from Lugar where codParque = '"+nombre+"'");
        q.executeUpdate();
        session.getTransaction().commit();
    }
	public void borrarLugarTodo() {
		session.beginTransaction();
        Query q = session.createQuery("delete from Lugar");
        q.executeUpdate();
        session.getTransaction().commit();
    }
}
