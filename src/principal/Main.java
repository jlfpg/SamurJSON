package principal;

import java.util.Scanner;
import controlador.Intermediario;
import auxiliares.HibernateUtil;

public class Main {

	public static void main(String[] args){
    	int opcion = 0;
    	System.out.println("Seleccione su acceso a datos:");
    	System.out.println("0.- Salir");
    	System.out.println("1.- Ficheros Texto");
    	System.out.println("2.- AccesoJDBC");
    	System.out.println("3.- AccesoHibernate");
    	System.out.println("4.- AccesoJson");

    	Scanner sc = new Scanner(System.in);
    	opcion = sc.nextInt();
    	
    	if(opcion == 0){
    		System.out.println("Adios.");
    	}
    	else if(opcion == 1){
    		System.out.println("Interfaz por consola no implementada, aunque el codigo correspondiente a esta parte entregada en anteriores actividades si que esta.");
        	}
    	else if(opcion == 2){
    		System.out.println("Interfaz por consola no implementada, aunque el codigo correspondiente a esta parte entregada en anteriores actividades si que esta.");
        	}
    	else if(opcion == 3){
    		System.out.println("Interfaz por consola no implementada, aunque el codigo correspondiente a esta parte entregada en anteriores actividades si que esta.");
        	}
    	else if(opcion == 4){
    	Intermediario intermediario = new Intermediario();
    	intermediario.ejecucion();
    	}
    }

}