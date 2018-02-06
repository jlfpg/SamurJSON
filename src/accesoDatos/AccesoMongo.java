package accesoDatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


import controlador.IntermediarioM;
import modelo.Instalacion;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


public class AccesoMongo implements Datos {
	IntermediarioM intermedario;
	MongoClient mongoClient = new MongoClient("localhost", 27017);
	MongoDatabase db = mongoClient.getDatabase("instalacion");
	MongoCollection collection = db.getCollection("instalacion");
	int contador = 0;
	ArrayList<Instalacion> jugadoresfin = new ArrayList<Instalacion>();

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

	public HashMap<Integer, Instalacion> obtenerInstalacionM() {

		HashMap<Integer, Instalacion> instalacionesCreadas = new HashMap<Integer, Instalacion>();

		Instalacion instalacion;

		String nombre, direccion;
		int codparque, telefonoM;

		try {

			// PASO 3: Obtenemos una coleccion para trabajar con ella
			collection = db.getCollection("instalacion");

			// PASO 4.2.1: "READ" -> Leemos todos los documentos de la base de
			// datos
			int numDocumentos = (int) collection.count();
			System.out.println("Número de documentos (registros) en la colección instalacion: " + numDocumentos + "\n");

			// Busco todos los documentos de la colección, creo el objeto
			// deposito y lo almaceno en el hashmap
			MongoCursor<Document> cursor = collection.find().iterator();

			while (cursor.hasNext()) {
				Document rs = cursor.next();

				codparque = rs.getInteger("codparque");
				nombre = rs.getString("nombre");
				telefonoM = rs.getInteger("telefono");
				direccion = rs.getString("direccion");
				

				instalacion = new Instalacion(codparque, nombre, telefonoM, direccion);

				instalacionesCreadas.put(codparque, instalacion);

			}
		} catch (Exception ex) {
			System.out.println("Error leyendo la coleccion: no se ha podido acceder a los datos");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			System.out.println("Fin de la ejecucion del programa");
			System.exit(1);
		}

		return instalacionesCreadas;
	}

	@Override
	public void guardarInstalacionM(Instalacion instalacion) {
		try {
			Document document = new Document();
			document.put("codparque",instalacion.getCodparque() );
			document.put("nombre", instalacion.getNombre());
			document.put("telefono", instalacion.getTelefonoM());
			document.put("direccion", instalacion.getDireccion());

			collection.insertOne(document);
			mongoClient.close();
			
		} catch (Exception e) {
			System.out.println("Opcion guardar datos de la instalacion no disponible");
			e.printStackTrace();
		}
	}	
	
	@Override
	public void eliminarInstalacionM(Instalacion instalacion) {
		try {
		Document document = new Document();
		document.put("codparque", instalacion.getCodparque());
		

		System.out.println(document);
		collection.deleteOne(document);
 
		mongoClient.close();
		} catch (Exception e) {
			System.out.println("Opcion borrar datos de la instalacion no disponible");
			e.printStackTrace();
		}
	}
	
	@Override
	public void actualizarInstalacionM(Instalacion instalacion) {
		try {
			collection = db.getCollection("instalacion");
			
			Document filter = new Document("codparque", instalacion.getCodparque());
			Document document = new Document();
			
			document.put("nombre", instalacion.getNombre());
			document.put("telefono", instalacion.getTelefonoM());
			document.put("direccion", instalacion.getDireccion());
			
			Bson updateOperationDocument = new Document("$set", document);
			collection.updateOne(filter, updateOperationDocument);
			
			mongoClient.close();
			
		} catch (Exception e) {
			System.out.println("Opcion actualizar datos de la instalacion no disponible");
			e.printStackTrace();
		}
	}	

	Document depToDocument(Instalacion auxIns) {
		// Creamos una instancia Documento
		Document dbObjectDeposito = new Document();

		dbObjectDeposito.append("codparque", auxIns.getCodparque());
		dbObjectDeposito.append("nombre", auxIns.getNombre());
		dbObjectDeposito.append("telefono", auxIns.getTelefonoM());
		dbObjectDeposito.append("direccion", auxIns.getDireccion());

		return dbObjectDeposito;
	}

	@Override
	public HashMap<Integer, Instalacion> obtenerInstalacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean guardarInstalacion(HashMap<Integer, Instalacion> instalacion) {
		// TODO Auto-generated method stub
		return false;
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
