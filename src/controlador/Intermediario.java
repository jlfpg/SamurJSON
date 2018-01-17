package controlador;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.ietf.jgss.Oid;
import org.json.simple.JSONObject;

import modelo.Instalacion;
import accesoDatos.AccesoJSONRemoto;

public class Intermediario {

	Scanner teclado;
	AccesoJSONRemoto acceso;

	public Intermediario() {
		this.teclado = new Scanner(System.in); 
		this.acceso = new AccesoJSONRemoto();
	}

	public void ejecucion() {
		int op = 0; // Opcion
		boolean salir = false;

		while (!salir) { 
			System.out.println();
			System.out.println("........ MENU ........... \n" + ".  0 Salir \n" + ".  1 Leer Instalaciones  \n"
					+ ".  2 A�adir Instalacion \n" + ".  3 Borrar Instalacion \n" + ".  4 Actualizar Instalacion \n" + "..........................");
			try {
				op = teclado.nextInt();
				teclado.nextLine();
				System.out.println("OPCION SELECCIONADA:" + op);
				switch (op) {
				case 0:
					salir = true;
					break;
				case 1:
					HashMap<Integer, Instalacion> hm = leeInstalacion();
					pintaInstalaciones(hm);
					break;
				case 2:
					Instalacion auxIns = this.crearInstalacion();
					acceso.anadirInstalacion(auxIns);
					break;
				case 3:
					Instalacion auxInst = this.borrarInstalacion();
					acceso.delInstalacion(auxInst);
				case 4:
					Instalacion auxInsta = this.updateInstalacion();
					acceso.upInstalacion(auxInsta);
				default:
					System.out.println("Opcion invalida: marque un numero de 0 a 4");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Excepcion por opcion invalida: marque un numero de 0 a 4");
				teclado.next();
			} catch (Exception e) {
				System.out.println(
						"Excepcion desconocida. Traza de error comentada en el m�todo 'ejecucion' de la clase intermediario");
				System.out.println("Fin ejecuci�n");
				System.exit(-1);
			}
		}

		// teclado.close();

	}

	private HashMap<Integer, Instalacion> leeInstalacion() {

		HashMap<Integer, Instalacion> hmAux = acceso.lee();
		return hmAux;

	}

	private void pintaInstalaciones(HashMap<Integer, Instalacion> map) {


		for (Map.Entry<Integer, Instalacion> entry : map.entrySet()) {
			System.out.println(entry.getValue());
		}
	}

	private Instalacion crearInstalacion() {
		String nombre;
		String telefono;
		String direccion;
		Instalacion jAux = null;

		try {

			System.out.println("Escriba el nombre de la instalacion a a�adir:");
			nombre = teclado.nextLine();
			nombre=nombre;
			System.out.println("Escriba el telefono a a�adir:");
			telefono = teclado.nextLine();
			telefono=telefono;
			System.out.println("Escriba la direccion a a�adir:");
			direccion = teclado.nextLine();
			direccion=direccion;

			jAux = new Instalacion(nombre, telefono, direccion);
			
		} catch (InputMismatchException e) {
			System.out.println("Excepcion por opcion invalida: marque un numero de 0 a 4");
			teclado.next();
		}

		return jAux;
		
	}
	
	private Instalacion borrarInstalacion() {
		int codparque;
		Instalacion jAux = null;
		
		try {

			System.out.println("Escriba el codigo de la Instalacion que quiere borrar:");
			codparque = teclado.nextInt();
			codparque=codparque;
			
			jAux = new Instalacion(codparque);

		} catch (InputMismatchException e) {
			System.out.println("Excepcion por opcion invalida: marque un numero de 0 a 4");
			teclado.next();
		}

		return jAux;

		
	}
	
	private Instalacion updateInstalacion() {
		int codparque;
		String nombre;
		String telefono;
		String direccion;
		Instalacion jAux = null;

		try {

			System.out.println("Escriba el codigo de la Instalacion que quiere actualizar:");
			codparque = Integer.parseInt(teclado.nextLine());
			codparque=codparque;
			
			System.out.println("Escriba el nombre de la instalacion a a�adir:");
			nombre = teclado.nextLine();
			nombre=nombre;
			
			System.out.println("Escriba el telefono a a�adir:");
			telefono = teclado.nextLine();
			telefono=telefono;
			
			System.out.println("Escriba la direccion a a�adir:");
			direccion = teclado.nextLine();
			direccion=direccion;
			

			jAux = new Instalacion(codparque,nombre,telefono, direccion);

		} catch (InputMismatchException e) {
			System.out.println("Excepcion por opcion invalida: marque un numero de 0 a 4");
			teclado.next();
		}

		return jAux;

	}

}
