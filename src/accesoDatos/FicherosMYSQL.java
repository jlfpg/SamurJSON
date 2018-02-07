package accesoDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.SortedSet;

import modelo.Empleado;
import modelo.Instalacion;
import modelo.Notificacion;

public class FicherosMYSQL implements accesoDatos.Datos {

	File fEmpl;
	File fNoti;
	File fInsta;

	public FicherosMYSQL() {
		System.out.println("ACCESO a DATOS con FICHEROS");
	}

	

	@Override
	public HashMap<Integer, Instalacion> obtenerInstalacion() {

		HashMap<Integer, Instalacion> instalacionesCreadas = null;
		FileReader frInsta = null;
		BufferedReader br = null;

		try {
			fInsta = new File("./Ficheros/Datos/Instalacion.txt");
			instalacionesCreadas = new HashMap<Integer, Instalacion>();
			frInsta = new FileReader(fInsta);
			br = new BufferedReader(frInsta);
			// int condInterno;
			String linea;
			int codParque;
			String nombre;
			String telefono;
			String direccion;
			Instalacion nuevaInstalacion = null;
			while ((linea = br.readLine()) != null) {

				String[] datosIntalacion = linea.split(";");
				if (datosIntalacion.length == 3) { // Si se leen menos o mas datos es que ha habido un error. ¿Lanzar
													// excepcion?
					nombre = datosIntalacion[0];
					codParque = Integer.parseInt(datosIntalacion[1]);
					telefono = datosIntalacion[2];
					direccion = datosIntalacion[3];
					instalacionesCreadas.put(codParque, nuevaInstalacion);

				} else {
					throw new Exception();
				}
			}

			if (null != frInsta) {
				frInsta.close();
				br.close();
			}

		} catch (Exception e) {

			System.out.println("Error leyendo el fichero: no se ha podido acceder a los datos");
			System.out.println("Fin de la ejecucion del programa");
			System.exit(1);
		}
		System.out.println("Leidos datos del fichero Depositos");
		return instalacionesCreadas;

	}

	@Override
	public boolean guardarInstalacion(HashMap<Integer, Instalacion> instalaciones) {
		FileWriter fwInsta;
		boolean todoOK = true;

		try {
			fwInsta = new FileWriter(fInsta);

			Instalacion auxInsta;
			String linea;
			Integer codIntalacion;

			for (HashMap.Entry<Integer, Instalacion> entry : instalaciones.entrySet()) {
				auxInsta = (Instalacion) entry.getValue();
				codIntalacion = entry.getKey();
				linea = codIntalacion + ";" + ";" + auxInsta.getNombre() + ";" + auxInsta.getTelefono() + ";"
						+ auxInsta.getDireccion();
				fwInsta.write(linea);

			}
			fwInsta.close();
		} catch (Exception e) {
			todoOK = false;
		}

		return todoOK;
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
