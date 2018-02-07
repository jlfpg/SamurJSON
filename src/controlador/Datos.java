package controlador;

import java.util.HashMap;

import modelo.Instalacion;

public interface Datos {
	public HashMap<String, modelo.Empleado> obtenerEmpleados();

	public boolean guardarEmpleados(HashMap<String, modelo.Empleado> empleados);
	

	public HashMap<Integer, modelo.Notificacion> obtenerNotificaciones();

	public boolean guardarNotificaciones(HashMap<Integer, modelo.Notificacion> notificaciones);
	

	public HashMap<Integer, modelo.InstalacionF> obtenerInstalaciones();

	public boolean guardarInstalaciones(HashMap<Integer, modelo.InstalacionF> intalaciones);

}
