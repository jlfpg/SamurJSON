package modelo;

public class Notificacion {

	private int codNotificacion;
	private String direccion;
	private String urgencia;
	private String tipo;
	
	
	public Notificacion(String direccion, String urgencia, String tipo){
		this.direccion = direccion;
		this.urgencia = urgencia;
		this.tipo = tipo;
		
		
	}


	public int getCodNotificacion() {
		return codNotificacion;
	}


	public void setCodNotificacion(int codNotificacion) {
		this.codNotificacion = codNotificacion;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getUrgencia() {
		return urgencia;
	}


	public void setUrgencia(String urgencia) {
		this.urgencia = urgencia;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
