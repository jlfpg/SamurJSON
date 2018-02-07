package accesoDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.SortedSet;

import modelo.Empleado;
import modelo.InstalacionF;
import modelo.Notificacion;

public class FicherosMYSQL implements controlador.Datos {

	File fEmpl;
	File fNoti;
	File fInsta;

	public FicherosMYSQL() {
		System.out.println("ACCESO a DATOS con FICHEROS");
	}

	@Override
	public HashMap<String, Empleado> obtenerEmpleados() {

		HashMap<String, Empleado> empleadosCreados = null;
		FileReader frEmpl = null;
		BufferedReader br = null;

		try {
			fEmpl = new File("../Ficheros/Datos/Empleado.txt");
			empleadosCreados = new HashMap<String, Empleado>();
			frEmpl = new FileReader(fEmpl);
			br = new BufferedReader(frEmpl);
			// int condInterno;
			String linea;
			String dni;
			String nombre;
			String apellido;
			String fechaNacimiento;
			Empleado nuevoEmpleado;
			while ((linea = br.readLine()) != null) {

				String[] datosEmpleados = linea.split(";");
				if (datosEmpleados.length == 3) { // Si se leen menos o mas datos es que ha habido un error. ¿Lanzar
													// excepcion?
					dni = datosEmpleados[0];
					nombre = datosEmpleados[1];
					apellido = datosEmpleados[3];
					fechaNacimiento = datosEmpleados[4];
					nuevoEmpleado = new Empleado(dni, nombre, apellido, fechaNacimiento);
					empleadosCreados.put(dni, nuevoEmpleado);

				} else {
					throw new Exception();
				}
			}

			if (null != frEmpl) {
				frEmpl.close();
				br.close();
			}

		} catch (Exception e) {

			System.out.println("Error leyendo el fichero: no se ha podido acceder a los datos");
			System.out.println("Fin de la ejecucion del programa");
			System.exit(1);
		}
		System.out.println("Leidos datos del fichero Depositos");
		return empleadosCreados;
	}

	@Override
	public boolean guardarEmpleados(HashMap<String, Empleado> empleados) {

		FileWriter fwEmple;
		boolean todoOK = true;

		try {
			fwEmple = new FileWriter(fEmpl);

			Empleado auxEmple;
			String linea;
			String dni;

			for (HashMap.Entry<String, Empleado> entry : empleados.entrySet()) {
				auxEmple = (Empleado) entry.getValue();
				dni = entry.getKey();
				linea = dni + ";" + auxEmple.getDni() + ";" + auxEmple.getNombre() + ";" + auxEmple.getApellido() + ";"
						+ auxEmple.getFechaNacimiento();
				fwEmple.write(linea);

			}
			fwEmple.close();
		} catch (Exception e) {
			todoOK = false;
		}

		return todoOK;
	}

	@Override
	public HashMap<Integer, Notificacion> obtenerNotificaciones() {

		HashMap<Integer, Notificacion> notificacionesCreadas = null;
		FileReader frNot = null;
		BufferedReader br = null;

		try {
			fNoti = new File("../Ficheros/Datos/Notificacion.txt");
			notificacionesCreadas = new HashMap<Integer, Notificacion>();
			frNot = new FileReader(fNoti);
			br = new BufferedReader(frNot);
			// int condInterno;
			String linea;
			int codNotificacion;
			String direccion;
			String urgencia;
			String tipo;
			Notificacion nuevaNotificacion;
			while ((linea = br.readLine()) != null) {

				String[] datosNotificacion = linea.split(";");
				if (datosNotificacion.length == 3) { // Si se leen menos o mas datos es que ha habido un error. ¿Lanzar
														// excepcion?
					direccion = datosNotificacion[0];
					codNotificacion = Integer.parseInt(datosNotificacion[1]);
					urgencia = datosNotificacion[2];
					tipo = datosNotificacion[3];
					nuevaNotificacion = new Notificacion(direccion, urgencia, tipo);
					notificacionesCreadas.put(codNotificacion, nuevaNotificacion);

				} else {
					throw new Exception();
				}
			}

			if (null != frNot) {
				frNot.close();
				br.close();
			}

		} catch (Exception e) {

			System.out.println("Error leyendo el fichero: no se ha podido acceder a los datos");
			System.out.println("Fin de la ejecucion del programa");
			System.exit(1);
		}
		System.out.println("Leidos datos del fichero Depositos");
		return notificacionesCreadas;

	}

	@Override
	public boolean guardarNotificaciones(HashMap<Integer, Notificacion> notificaciones) {
		FileWriter fwNoti;
		boolean todoOK = true;

		try {
			fwNoti = new FileWriter(fNoti);

			Notificacion auxNoti;
			String linea;
			Integer codNotificacion;

			for (HashMap.Entry<Integer, Notificacion> entry : notificaciones.entrySet()) {
				auxNoti = (Notificacion) entry.getValue();
				codNotificacion = entry.getKey();
				linea = codNotificacion + ";" + auxNoti.getDireccion() + ";" + auxNoti.getUrgencia() + ";"
						+ auxNoti.getTipo();
				fwNoti.write(linea);

			}
			fwNoti.close();
		} catch (Exception e) {
			todoOK = false;
		}

		return todoOK;
	}

	@Override
	public HashMap<Integer, InstalacionF> obtenerInstalaciones() {

		HashMap<Integer, InstalacionF> instalacionesCreadas = null;
		FileReader frInsta = null;
		BufferedReader br = null;

		try {
			fInsta = new File("../Ficheros/Datos/Instalacion.txt");
			instalacionesCreadas = new HashMap<Integer, InstalacionF>();
			frInsta = new FileReader(fInsta);
			br = new BufferedReader(frInsta);
			// int condInterno;
			String linea;
			int codParque;
			String nombre;
			String telefono;
			String direccion;
			InstalacionF nuevaInstalacion = null;
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
	public boolean guardarInstalaciones(HashMap<Integer, InstalacionF> instalaciones) {
		FileWriter fwInsta;
		boolean todoOK = true;

		try {
			fwInsta = new FileWriter(fInsta);

			InstalacionF auxInsta;
			String linea;
			Integer codIntalacion;

			for (HashMap.Entry<Integer, InstalacionF> entry : instalaciones.entrySet()) {
				auxInsta = (InstalacionF) entry.getValue();
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
}
