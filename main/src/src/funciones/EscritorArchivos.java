package funciones;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class EscritorArchivos {
	
	FileWriter archivo = null;
    PrintWriter pw = null;
    int cantstrings;
    
    public EscritorArchivos(String nombreArchivo) throws IOException
    {
    	archivo = new FileWriter(nombreArchivo);
    	pw = new PrintWriter(archivo);
    	cantstrings=0;
    }
    
    public void EscribirEnArchivo(String cadena)
    {
    	pw.println(cadena);
    	pw.flush();
    }
        
    public void CerrarArchivo() throws IOException
    {
    	if (null != archivo) archivo.close();       
    }
}
