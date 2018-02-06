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
	AccesoMongo acceso;
	public String nombre, direccion;
	Datos datos;
	HashMap<Integer, Instalacion> hm = new HashMap<>();
	public int codparque, telefonoM;

	public IntermediarioM() {
		this.teclado = new Scanner(System.in);
		this.acceso = new AccesoMongo();
	}

	public void ejecucion() {
		int op = 0; // Opcion
		boolean salir = false;

		while (!salir) {
			System.out.println();
			System.out.println("........ MENU ........... \n" + ".  0 Salir \n" + ".  1 Leer Instalaciones  \n"
					+ ".  2 Aï¿½adir Instalacion \n" + ".  3 Borrar Instalacion \n" + ".  4 Actualizar Instalacion \n"
					+ "..........................");
			try {
				op = teclado.nextInt();
				teclado.nextLine();
				System.out.println("OPCION SELECCIONADA:" + op);
				switch (op) {
				case 0:
					salir = true;
					break;
				case 1:
					hm = datos.obtenerInstalacionM();
					pintaInstalacion(hm);
					break;
				case 2:
					Instalacion instalacion = crearInstalacion();
					hm.put(codparque, instalacion);
					datos.guardarInstalacionM(instalacion);
					break;
				case 3:
					Instalacion instalacion1 = eliminarInstalacion();
					hm.remove(codparque, instalacion1.getCodparque());
					datos.eliminarInstalacionM(instalacion1);
					break;
				case 4:
					Instalacion instalacion2 = actualizarInstalacion();
					datos.actualizarInstalacionM(instalacion2);
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
						"Excepcion desconocida. Traza de error comentada en el mï¿½todo 'ejecucion' de la clase intermediario");
				System.out.println("Fin ejecuciï¿½n");
				System.exit(-1);
			}
		}

		// teclado.close();

	}

	private void pintaInstalacion(HashMap<Integer, Instalacion> map) {


		for (Map.Entry<Integer, Instalacion> entry : map.entrySet()) {

			Instalacion insAux = entry.getValue();
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodparque() {
		return codparque;
	}

	public void setCodparque(int codparque) {
		this.codparque = codparque;
	}

	public int getTelefonoM() {
		return telefonoM;
	}

	public void setTelefonoM(int telefonoM) {
		this.telefonoM = telefonoM;
	}

}
