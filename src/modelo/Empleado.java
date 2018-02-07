package modelo;

public class Empleado {

	private int condInterno;
	private String dni;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	
	
	public  Empleado(String dni, String nombre, String apellido, String fechaNacimiento){
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		
	}


	public int getCondInterno() {
		return condInterno;
	}


	public void setCondInterno(int condInterno) {
		this.condInterno = condInterno;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
	
	
}
