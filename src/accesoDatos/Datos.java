package accesoDatos;


import java.util.HashMap;
import modelo.Instalacion;

public interface Datos {
	
	public HashMap<Integer, Instalacion> obtenerInstalacionM();
	public void guardarInstalacionM(Instalacion instalacion);
	public void eliminarInstalacionM(Instalacion instalacion);
	public void actualizarInstalacionM(Instalacion instalacion);
	public void eliminarTodo();

	public HashMap<Integer, Instalacion> obtenerInstalacion();

	public boolean guardarInstalacion(HashMap<Integer, Instalacion> instalacion);

	public boolean updateInstalacion(HashMap<Integer, Instalacion> instalacion);

	public boolean deleteInstalacion(HashMap<Integer, Instalacion> instalacion);

	
		
}