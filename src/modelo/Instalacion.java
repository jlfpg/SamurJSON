package modelo;

public class Instalacion {
	
	int codparque;
	String nombre;
	String telefono;
	String direccion;
		
	/* Constructores */

	public Instalacion() {

	}	
	
	public Instalacion(int codparque, String nombre, String telefono, String direccion) {
		this.codparque = codparque;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		
	}
	
	public Instalacion(String nombre,String telefono ,String direccion) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;

	}
	
	public Instalacion(int codparque) {
		this.codparque = codparque;
		
	}
	
	/* Getters & Setters*/
	
	public int getCodParque() {
		return codparque;
	}

	public void setCodParque(int codParque) {
		this.codparque = codParque;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	public String toString(){
		String aux ="";
		
		aux += "------------------------------------------";
		aux += "\n	CODIGO: " + this.codparque;
		aux += "\n	NOMBRE: " + this.nombre;
		aux += "\n	TELEFONO: " + this.telefono;
		aux += "\n	DIRECCION: " + this.direccion;
		aux += "\n------------------------------------------";
		
		return aux;
	}
	
}
