package org.example;

import java.awt.geom.Point2D;
import java.io.IOException;

/*
 * Clase encargada de modelar el universo donde se encuentran los planetas. Por simplicidad, se escogio que las caracteristicas de los planetas no sean modificables.
 * Para representar los planetas se utiliza una abstraccion de que cada planeta se encuentra en una coordenada XY, siendo el origen (0,0) el sol del sistema planetario.
 */

public class universo {

	private Planeta ferengi,betasoide,vulcano;
	
	public universo() throws IOException {
		ferengi = new Planeta(5, 1, "Ferengi", 'h');
		betasoide = new Planeta(20, 3, "Betasoide", 'h');
		vulcano = new Planeta(10, 5, "Vulcano", 'a');
	}

	public Planeta getFerengi() {
		return ferengi;
	}
	
	public void setFerengi(Planeta ferengi) {
		this.ferengi = ferengi;
	}

	public Planeta getBetasoide() {
		return betasoide;
	}

	public void setBetasoide(Planeta betasoide) {
		this.betasoide = betasoide;
	}

	public Planeta getVulcano() {
		return vulcano;
	}

	public void setVulcano(Planeta vulcano) {
		this.vulcano = vulcano;
	}
	
	/*
	 * Calcula que los tres planetas esten alineados, sin contar al sol. 
	 */
	
	public boolean planetas_alineados ()
	{
		//Calcula si los tres planetas estan alineados, no cuenta al sol
		Point2D.Double pf,pb,pv;
		pf = ferengi.getPunto_actual();
		pb = betasoide.getPunto_actual();
		pv = vulcano.getPunto_actual();
			
		double result = pf.getX() * (pb.getY() - pv.getY()) + pb.getX() * (pv.getY() - pf.getY()) + pv.getX() * (pf.getY() - pb.getY());
		
		double result2 = pb.getX() * (pf.getY() - pv.getY()) + pf.getX() * (pv.getY() - pb.getY()) + pv.getX() * (pb.getY() - pf.getY());
		
		return (result < 5 && result > -5) || (result2 < 7.5 && result2 > -7.5);
	}
	
	public Point2D.Double[] puntos_actuales()
	{
		Point2D.Double[] puntos = {ferengi.getPunto_actual(), betasoide.getPunto_actual(), vulcano.getPunto_actual()};
		return puntos;
	}
	
	/*
	 * Calcula si todos los planetas y el sol se encuentran alineados.
	 */
	
	public boolean todos_alineados()
	{
		//Calcula si los todas las entidades estan alineadas
		Point2D.Double pf,pb,pv,ps;
		pf = ferengi.getPunto_actual();
		pb = betasoide.getPunto_actual();
		pv = vulcano.getPunto_actual();
		ps = new Point2D.Double(0, 0);
				
		double result = pf.getX() * (pb.getY() - pv.getY()) + pb.getX() * (pv.getY() - pf.getY()) + pv.getX() * (pf.getY() - pb.getY());
		
		boolean estan_alineados = result < 7.5 && result > -7.5;
		
		double result2 = ps.getX() * (pb.getY() - pv.getY()) + pb.getX() * (pv.getY() - ps.getY()) + pv.getX() * (ps.getY() - pb.getY());
		
		boolean estan_alineados_2 = result2 < 7.5 && result2 > -7.5;
		
		return estan_alineados && estan_alineados_2;
	}
	
	/*
	 * Calcula el perimetro del triangulo formado por los planetas, dado por la suma de la distancia de los puntos entre si.
	 */
	
	public double perimetro_planetas ()
	{
		double distancia = 0;
		distancia += ferengi.getPunto_actual().distance(betasoide.getPunto_actual()) + vulcano.getPunto_actual().distance(betasoide.getPunto_actual()) + ferengi.getPunto_actual().distance(vulcano.getPunto_actual());
		return distancia;
	}
	
	/*
	 * Calcula si el sol se encuentra dentro del triangulo formado por los tres planetas. Se basa en la equalidad de la orientacion de los triangulos.
	 */
	
	public boolean el_sol_esta_dentro()
	{
		//Calculo la orientacion del triangulo actual
		Point2D.Double pf,pb,pv,ps;
		pf = ferengi.getPunto_actual();
		pb = betasoide.getPunto_actual();
		pv = vulcano.getPunto_actual();
		ps = new Point2D.Double(0, 0);
		
		double orientacion_digito = ((pf.getX() - pv.getX()) * (pb.getY() - pv.getY())) - ((pf.getY() - pv.getY()) * (pb.getX() - pv.getX()));
		
		if (orientacion_digito < 0)
		{
			//Orientacion negativa
			double orientacion_digito_2 = ((pf.getX() - ps.getX()) * (pb.getY() - ps.getY())) - ((pf.getY() - ps.getY()) * (pb.getX() - ps.getX()));
			double orientacion_digito_3 = ((pf.getX() - pv.getX()) * (ps.getY() - pv.getY())) - ((pf.getY() - pv.getY()) * (ps.getX() - pv.getX()));
			double orientacion_digito_4 = ((ps.getX() - pv.getX()) * (pb.getY() - pv.getY())) - ((ps.getY() - pv.getY()) * (pb.getX() - pv.getX()));
			
			if (orientacion_digito_2 < 0 && orientacion_digito_3 < 0 && orientacion_digito_4 < 0 )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			//Orientacion positiva
			double orientacion_digito_2 = ((pf.getX() - ps.getX()) * (pb.getY() - ps.getY())) - ((pf.getY() - ps.getY()) * (pb.getX() - ps.getX()));
			double orientacion_digito_3 = ((pf.getX() - pv.getX()) * (ps.getY() - pv.getY())) - ((pf.getY() - pv.getY()) * (ps.getX() - pv.getX()));
			double orientacion_digito_4 = ((ps.getX() - pv.getX()) * (pb.getY() - pv.getY())) - ((ps.getY() - pv.getY()) * (pb.getX() - pv.getX()));
			
			if (orientacion_digito_2 >= 0 && orientacion_digito_3 >= 0 && orientacion_digito_4 >= 0 )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	/*
	 * 
	 */
	
	public String calcular_clima ()
	{
		ferengi.proximo_dia();
		betasoide.proximo_dia();
		vulcano.proximo_dia();
		
		if (todos_alineados())
		{
			return "Sequia";
		}
		else if (planetas_alineados())
		{
			return "Optima";
		}
		else
		{
			//Forman un triangulo
			if (el_sol_esta_dentro()) {
				return "Lluvia";
			}
			else 
			{
				return "Normal";
			}
		}
	}	
}
