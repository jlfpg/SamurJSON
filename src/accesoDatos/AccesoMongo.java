package accesoDatos;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.*;
import com.mongodb.client.*;

import modelo.Instalacion;

public class AccesoMongo implements Datos {

	MongoClient mongoClient;
	MongoCollection<Document> collection;
	MongoDatabase db;

	public AccesoMongo() {
		try {
			// PASO 1: Conexión al Server de MongoDB Pasandole el host y el
			// puerto
			mongoClient = new MongoClient("localhost", 27017);

			// PASO 2: Conexión a la base de datos
			db = mongoClient.getDatabase("instalacion");
			System.out.println("Conectado a BD MONGO");

		} catch (Exception e) {
			System.out.println("Error leyendo la BD MONGO: " + e.getMessage());
			System.out.println("Fin de la ejecucion del programa");
			System.exit(1);
		}

	}

	@Override
	public HashMap<Integer, Instalacion> obtenerInstalacion() {

		HashMap<Integer, Instalacion> instalacionCreada = new HashMap<Integer, Instalacion>();

		Instalacion nuevoIns;
		String nombre;
		int telefonoM;
		String direccion;
		try {

			// PASO 3: Obtenemos una coleccion para trabajar con ella
			collection = db.getCollection("instalacion");

			// PASO 4.2.1: "READ" -> Leemos todos los documentos de la base de
			// datos
			int numDocumentos = (int) collection.count();
			System.out.println("Número de documentos (registros) en la colección depositos: " + numDocumentos + "\n");

			// Busco todos los documentos de la colección, creo el objeto
			// deposito y lo almaceno en el hashmap
			MongoCursor<Document> cursor = collection.find().iterator();

			while (cursor.hasNext()) {
				Document rs = cursor.next();
				nombre = rs.getString("nombre");
				telefonoM = rs.getInteger("telefono");
				direccion = rs.getString("direccion");
				nuevoIns = new Instalacion(nombre, telefonoM, direccion);
				// Una vez creado el deposito con valor de la moneda y cantidad
				// lo metemos en el hashmap
				instalacionCreada.put(telefonoM, nuevoIns);

				// System.out.println(cursor.next().toString());
			}
		} catch (Exception ex) {
			System.out.println("Error leyendo la coleccion: no se ha podido acceder a los datos");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			System.out.println("Fin de la ejecucion del programa");
			System.exit(1);
		}

		System.out.println("Leidos datos de la coleccion de Depositos");
		return instalacionCreada;
	}

	@Override
	public boolean guardarInstalacion(HashMap<Integer, Instalacion> instalacion) {
		boolean todoOK = false;

		todoOK = this.guardarIns(instalacion);

		return todoOK;

	}

	// Actualizamos borrando la colleccion y volviendo a escribir
	private boolean guardarIns(HashMap<Integer, Instalacion> instalacion) {
		boolean todoOK = true;

		try {
			Instalacion auxIns;
			collection = db.getCollection("instalacion");

			// Para que salga ordenado el hashmap de monedas (de stackoverflow)
			SortedSet<Integer> keys = new TreeSet<Integer>(instalacion.keySet());
			for (int key : keys) {

				auxIns = (Instalacion) Instalacion.get(key);

				Bson filtro = new Document("telefono", auxIns.getTelefono());
				Bson cambio = new Document("nombre", auxIns.getNombre());
				Bson actualizacion = new Document("$set", cambio);

				collection.updateOne(filtro, actualizacion);

			}
		} catch (Exception e) {
			todoOK = false;
			System.out.println("Opcion guardar datos de Depositos no disponible por el momento");
			e.printStackTrace();
		}

		return todoOK;

	}

	private Document depToDocument(Instalacion auxIns) {
		// Creamos una instancia Documento
		Document dbObjectInstalacion = new Document();

		dbObjectInstalacion.append("nombre", auxIns.getNombre());
		dbObjectInstalacion.append("telefono", auxIns.getTelefono());
		dbObjectInstalacion.append("direccion", auxIns.getDireccion());

		return dbObjectInstalacion;
	}

	@Override
	public boolean updateInstalacion(HashMap<Integer, Instalacion> instalacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteInstalacion(HashMap<Integer, Instalacion> instalacion) {
		// TODO Auto-generated method stub
		return false;
	}

}
