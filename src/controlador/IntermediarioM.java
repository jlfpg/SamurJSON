package controlador;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import modelo.Instalacion;
import accesoDatos.Datos;
import accesoDatos.AccesoMongo;

public class IntermediarioM {

	Scanner teclado;
	AccesoMongo accesoM;
	Datos datos;
	HashMap<Integer, Instalacion> hm = new HashMap<>();
	public String nombre, direccion;
	public int codparque, telefonoM;

	public IntermediarioM() {
		this.teclado = new Scanner(System.in); // Para leer las opciones de //
												// teclado
		this.accesoM = new AccesoMongo();
	}

	public void ejecucion() {
		int op = 0; // Opcion
		int as = 0;
		int ops = 0;
		boolean salir = false;
		HashMap<Integer, Instalacion> auxJugador3 = null;
		datos = new AccesoMongo();
		while (!salir) { // Estructura que repite el algoritmo del menu

			System.out.println();
			System.out.println("........ MENU ........... \n" + ".  0 Salir \n" + ".  1 Leer Instalaciones  \n"
					+ ".  2 Aï¿½adir Instalacion \n" + ".  3 Borrar Instalacion \n" + ".  4 Actualizar Instalacion \n"
					+ ".  5 Borrar todas las instalaciones \n" + ".  6 Volcado de datos a ficheros \n" + "..........................");

			try {
				ops = teclado.nextInt();
				teclado.nextLine();
				System.out.println("OPCION SELECCIONADA:" + ops);
				switch (ops) {
				case 0:
					salir = true;
					break;
				case 1:
					hm = datos.obtenerInstalacionM();
					pintaInstalacion(hm);
					ejecucion();
				case 2:
					Instalacion instalacion = crearInstalacion();
					hm.put(codparque, instalacion);
					datos.guardarInstalacionM(instalacion);
					ejecucion();
				case 3:
					Instalacion instalacion1 = eliminarInstalacion();
					hm.remove(codparque, instalacion1.getCodparque());
					datos.eliminarInstalacionM(instalacion1);
					ejecucion();
				case 4:
					Instalacion instalacion2 = actualizarInstalacion();
					datos.actualizarInstalacionM(instalacion2);
					ejecucion();
				case 5:
					System.out.println("Estas seguro de que quieres borrar todas las instalaciones? Y/N");
					Scanner sc = new Scanner(System.in);
					String condicion = sc.nextLine();
					if(condicion.equals("Y")){
						AccesoMongo m = new AccesoMongo();
						m.eliminarTodo();
						System.out.println("Todas las instalaciones borradas");
						ejecucion();
					}else{
						System.out.println("No se ha borrado todo");
						ejecucion();	
					}
				case 6:
					System.out.println("Datos volcados en fichero 'datos.txt' que esta en la ruta Ficheros-Datos-datos.txt");
					AccesoMongo m1 = new AccesoMongo();
					m1.escribirFicheros();
					ejecucion();
				default:
					System.out.println("Opcion invalida: marque un numero de 0 a 4");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Excepcion por opcion invalida: marque un numero de 0 a 4");
				teclado.next();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(
						"Excepcion desconocida. Traza de error comentada en el método 'ejecucion' de la clase intermediario");
			}
			break;

		}
	}

	private void pintaInstalacion(HashMap<Integer, Instalacion> map) {

		for (Map.Entry<Integer, Instalacion> entry : map.entrySet()) {

			Instalacion insAux = entry.getValue();
			System.out.println(insAux);
		}

	}

	public Instalacion crearInstalacion() {

		Instalacion iAux = null;

		try {
			System.out.println("Escriba el codigo de la instalacion a añadir");
			codparque = Integer.parseInt(teclado.nextLine());
			System.out.println("Escriba el nombre de la instalacion a añadir");
			nombre = teclado.nextLine();
			System.out.println("Escriba el telefono de la instalacion a añadir");
			telefonoM = Integer.parseInt(teclado.nextLine());
			System.out.println("Escriba la direccion de la instalacion a añadir");
			direccion = teclado.nextLine();

			iAux = new Instalacion(codparque, nombre, telefonoM, direccion);

		} catch (InputMismatchException e) {
			System.out.println("Excepcion por opcion invalida: marque un numero de 0 a 4");
			teclado.next();
		}

		return iAux;

	}

	public Instalacion eliminarInstalacion() {
		Instalacion iAux1 = null;
		try {
			System.out.println("Escriba el codigo de la instalacion a eliminar");
			codparque = Integer.parseInt(teclado.nextLine());

			iAux1 = new Instalacion(codparque);

			System.out.println(iAux1);
		} catch (InputMismatchException e) {
			System.out.println("Excepcion por opcion invalida: marque un numero de 0 a 4");
			teclado.next();
		}
		return iAux1;
	}

	public Instalacion actualizarInstalacion() {
		Instalacion iAux2 = null;
		try {
			System.out.println("Escriba el codigo de la instalacion a actualizar");
			codparque = Integer.parseInt(teclado.nextLine());
			System.out.println("Escriba el nombre de la instalacion a actualizar");
			nombre = teclado.nextLine();
			System.out.println("Escriba el telefono de la instalacion a actualizar");
			telefonoM = Integer.parseInt(teclado.nextLine());
			System.out.println("Escriba la direccion de la instalacion a actualizar");
			direccion = teclado.nextLine();

			iAux2 = new Instalacion(codparque, nombre, telefonoM, direccion);
		} catch (InputMismatchException e) {
			System.out.println("Excepcion por opcion invalida: marque un numero de 0 a 4");
			teclado.next();
		}
		return iAux2;
	}

}
