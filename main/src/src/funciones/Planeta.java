package funciones;

import java.awt.geom.Point2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/*
 * Clase que modela a un planeta, para su inclusion en el sistema planetario.
 */

public class Planeta {
	private int radio_sol;
	private int grados_dia;
	private String nombre;
	private int dias_rotacion;
	private char sentido_rotacion;
	private Point2D.Double punto_actual;
	private int dias_actuales;
	private DecimalFormat df = new DecimalFormat("#.####");
	
	public Planeta(int rs, int gd, String nm, char sr)
	{
		radio_sol = rs;
		grados_dia = gd;
		nombre = nm;
		sentido_rotacion = sr;
		dias_rotacion = 360;
		punto_actual = new Point2D.Double(rs, 0);
		dias_actuales = 0;
		df.setRoundingMode(RoundingMode.CEILING);
	}

	public Point2D.Double getPunto_actual() {
		return punto_actual;
	}

	public void setPunto_actual(Point2D.Double punto_actual) {
		this.punto_actual = punto_actual;
	}

	public int getDias_actuales() {
		return dias_actuales;
	}

	public void setDias_actuales(int dias_actuales) {
		this.dias_actuales = dias_actuales;
	}

	public int getRadio_sol() {
		return radio_sol;
	}

	public void setRadio_sol(int radio_sol) {
		this.radio_sol = radio_sol;
	}

	public int getGrados_dia() {
		return grados_dia;
	}

	public void setGrados_dia(int grados_dia) {
		this.grados_dia = grados_dia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDias_rotacion() {
		return dias_rotacion;
	}

	public void setDias_rotacion(int dias_rotacion) {
		this.dias_rotacion = dias_rotacion;
	}

	public char getSentido_rotacion() {
		return sentido_rotacion;
	}

	public void setSentido_rotacion(char sentido_rotacion) {
		this.sentido_rotacion = sentido_rotacion;
	}
	
	public void proximo_dia() {
		//Mueve el contador de dias en uno, aplica los cambios necesarios
		//Mueve el punto actual, mueve el contador de dia
		
		dias_actuales = dias_actuales + 1;
		
		//Calculo el proximo punto al que tiene que ir.
		
		double x=0;
		double y=0;
		
		//Calculo el angulo del nuevo punto, segun la cantidad de dias que pasaron, y el sentido de la rotacion
		
		int angulo = (dias_actuales * grados_dia) % dias_rotacion;
		
		if ( sentido_rotacion == 'h')
		{
			
			x = Math.cos(Math.toRadians(angulo))*radio_sol;
			y = Math.sin(Math.toRadians(angulo))*radio_sol;
		}
		else if (sentido_rotacion == 'a')
		{
			angulo = 360 - angulo;
			x = Math.cos(Math.toRadians(angulo))*radio_sol;
			y = Math.sin(Math.toRadians(angulo))*radio_sol;
		}
		
		punto_actual = new Point2D.Double(round(x,1),round(y,1));		
	}
	
	private static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
}
