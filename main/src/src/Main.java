import java.io.IOException;

import funciones.*;


public class Main { 
	
	public static void main(String S[]){
		universo nuevo_universo;
		try {
			nuevo_universo = new universo();
			nuevo_universo.operacion_automatica(3560);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}