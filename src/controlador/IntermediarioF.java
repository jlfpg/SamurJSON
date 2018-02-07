package controlador;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import accesoDatos.Datos;
import accesoDatos.FicherosMYSQL;
import modelo.Empleado;
import modelo.Instalacion;
import modelo.Notificacion;

public class IntermediarioF {
	Scanner teclado;
	FicherosMYSQL acceso;
	//public String dni, direccion, nombre, apellido,fechaNacimiento;
	FicherosMYSQL fich = new FicherosMYSQL();
	HashMap<String, Empleado> hm = new HashMap<>();
	HashMap<Integer, Notificacion> hm2 = new HashMap<>();
	HashMap<Integer, Instalacion> hm3 = new HashMap<>();
	//public int codparque;

	public IntermediarioF() {
		this.teclado = new Scanner(System.in);
		this.acceso = new FicherosMYSQL();
	}

	public void ejecucion() {
		int op = 0;
		boolean salir = false;

		while (!salir) {
			System.out.println();
			System.out.println("........ MENU ........... \n" + ".  0 Salir \n" + ".  1 Instalaciones  \n"
					+ ".  2 Notificaciones \n" + ".  3 Instalaciones" 
					+ "..........................");

			try {
				op = teclado.nextInt();
				teclado.nextLine();
				System.out.println("OPCION SELECCIONADA: " + op);
				switch (op) {
				case 0:
					salir = true;
					break;
				
					
				case 1:
					fich.guardarInstalacion(hm3);
					fich.obtenerInstalacion();
					
					System.out.println(fich.obtenerInstalacion());
					break;
				default:
					System.out.println("Opcion invalida: marque un numero de 0 a 2");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Excepcion por opcion invalida: marque un numero de 0 a 2");
				teclado.next();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(
						"Excepcion desconocida. Traza de error comentada en el m�todo 'ejecucion' de la clase intermediario");
				System.out.println("Fin ejecuci�n");
				System.exit(-1);
			}
		}
	}

	
	
	
}
