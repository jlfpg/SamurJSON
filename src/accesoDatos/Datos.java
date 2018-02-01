package accesoDatos;

import java.util.ArrayList;
import java.util.HashMap;
import modelo.Instalacion;

public interface Datos {

	public HashMap<Integer, Instalacion> obtenerInstalacion();

	public boolean guardarInstalacion(HashMap<Integer, Instalacion> instalacion);

	public boolean updateInstalacion(HashMap<Integer, Instalacion> instalacion);

	public boolean deleteInstalacion(HashMap<Integer, Instalacion> instalacion);

}