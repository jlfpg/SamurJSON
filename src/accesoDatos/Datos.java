package accesoDatos;

import java.util.ArrayList;
import java.util.HashMap;
import modelo.Instalacion;
 
 
public interface Datos {
   
    public HashMap<Integer, Instalacion>  obtenerInstalacion();
    public boolean guardarInstalacion(HashMap<Integer, Instalacion> instalacion);
   
}