package funciones;

import java.awt.geom.Point2D;
import java.io.IOException;

/*
 * Clase encargada de modelar el universo donde se encuentran los planetas. Por simplicidad, se escogio que las caracteristicas de los planetas no sean modificables.
 * Para representar los planetas se utiliza una abstraccion de que cada planeta se encuentra en una coordenada XY, siendo el origen (0,0) el sol del sistema planetario.
 */

public class universo {

	private Planeta ferengi,betasoide,vulcano;
	private EscritorArchivos log1,log2,log3,log4, log5;
	private int cantidad_errados = 0;
	private boolean esta_lloviendo;
	private double maximo_perimetro_dia = 0;
	private int dia_maximo_perimetro_dia = 0;
	private double perimetro_actual_dia = 0;
	private int dia_actual;
	private boolean hay_que_conectar = false;
	private int periodos_de_sequia;
	private int dia_maximo_perimetro_global, periodos_de_lluvia;
	private double maximo_perimetro_global;
	private int periodos_ideales;
	private String status_text_dia_actual;
	
	public String getStatus_text_dia_actual() {
		return status_text_dia_actual;
	}

	public void setStatus_text_dia_actual(String status_text_dia_actual) {
		this.status_text_dia_actual = status_text_dia_actual;
	}

	public boolean isHay_que_conectar() {
		return hay_que_conectar;
	}

	public void setHay_que_conectar(boolean hay_que_conectar) {
		this.hay_que_conectar = hay_que_conectar;
	}

	public universo() throws IOException {
		ferengi = new Planeta(5, 1, "Ferengi", 'h');
		betasoide = new Planeta(20, 3, "Betasoide", 'h');
		vulcano = new Planeta(10, 5, "Vulcano", 'a');
		esta_lloviendo = false;
		dia_actual = 0;
		setPeriodos_de_sequia(0);
		maximo_perimetro_global = 0;
		dia_maximo_perimetro_global = 0;
		periodos_de_lluvia = 0;
		periodos_ideales = 0;
		status_text_dia_actual = "";
		log1=new EscritorArchivos("log1.txt");
		log2=new EscritorArchivos("log2.txt");
		log3=new EscritorArchivos("log3.txt");
		log4=new EscritorArchivos("log4.txt");
		log5=new EscritorArchivos("log5.txt");
	}

	public Planeta getFerengi() {
		return ferengi;
	}
	
	public int getDiaActual ()
	{
		return this.dia_actual;
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
		
		if (result < 5 && result > -5) {
			log4.EscribirEnArchivo(pf.toString());
			log4.EscribirEnArchivo(pb.toString());
			log4.EscribirEnArchivo(pv.toString());
			log4.EscribirEnArchivo("------------------------------------------");
			setCantidad_errados(getCantidad_errados() + 1);
		}
		
		double result2 = pb.getX() * (pf.getY() - pv.getY()) + pf.getX() * (pv.getY() - pb.getY()) + pv.getX() * (pb.getY() - pf.getY());
		
		if (result2 < 7.5 && result2 > -7.5) {
			log4.EscribirEnArchivo(pf.toString());
			log4.EscribirEnArchivo(pb.toString());
			log4.EscribirEnArchivo(pv.toString());
			log4.EscribirEnArchivo("------------------------------------------");
			setCantidad_errados(getCantidad_errados() + 1);
		}
		
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
		
		if (result < 5 && result > -5 && result2 < 5 && result2 > -5) {
			log5.EscribirEnArchivo(pf.toString());
			log5.EscribirEnArchivo(pb.toString());
			log5.EscribirEnArchivo(pv.toString());
			log5.EscribirEnArchivo("------------------------------------------");
			setCantidad_errados(getCantidad_errados() + 1);
		}
		
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
	 * Avanza el estado del sistema solar en cant_dias dias.
	 */
	
	public void operacion_automatica (int cant_dias)
	{
		double maximo_perimetro = 0;
		@SuppressWarnings("unused")
		int dia_maximo_perimetro = 0;
		double perimetro_actual = 0;
		for (int i = 1;i<=cant_dias;i++)
		{
			ferengi.proximo_dia();
			betasoide.proximo_dia();
			vulcano.proximo_dia();
			
			if (todos_alineados())
			{
				if (esta_lloviendo)
				{
					esta_lloviendo = false;
				}
				status_text_dia_actual = "El sol y los planetas se alinearon, hay sequia";
				hay_que_conectar = true;
				setPeriodos_de_sequia(getPeriodos_de_sequia() + 1);
			}
			else if (planetas_alineados())
			{
				if (esta_lloviendo)
				{
					esta_lloviendo = false;
				}
				status_text_dia_actual = "Presion y temperatura optimas!";
				hay_que_conectar = true;
				periodos_ideales++;
			}
			else
			{
				//Forman un triangulo
				if (el_sol_esta_dentro()) {
					if (!esta_lloviendo)
					{
						esta_lloviendo = true;
						dia_maximo_perimetro = i;
						maximo_perimetro = perimetro_planetas();
						if (maximo_perimetro_global < maximo_perimetro)
						{
							maximo_perimetro_global = maximo_perimetro;
							dia_maximo_perimetro_global = i;
						}
						periodos_de_lluvia++;
						status_text_dia_actual = "Comenzo a llover!";
					}
					else
					{
						perimetro_actual = perimetro_planetas();
						if (perimetro_actual > maximo_perimetro)
						{
							dia_maximo_perimetro = i;
							maximo_perimetro = perimetro_actual;
							if (maximo_perimetro_global < maximo_perimetro)
							{
								maximo_perimetro_global = maximo_perimetro;
								dia_maximo_perimetro_global = i;
							}
						}
						status_text_dia_actual = "Esta lloviendo!";
					}	
					hay_que_conectar = true;
				}
				else 
				{
					if (esta_lloviendo)
					{
						esta_lloviendo = false;
					}
					hay_que_conectar = false;
					status_text_dia_actual = "No pasa nada en particular";
				}
			}
		}
	}
	
	/*
	 * Avanza el estado del sistema solar en un dia.
	 */
	
	public Point2D.Double[] operacion_automatica_2 ()
	{
		dia_actual++;
		ferengi.proximo_dia();
		betasoide.proximo_dia();
		vulcano.proximo_dia();
		log1.EscribirEnArchivo("("+dia_actual+")"+" "+ferengi.getPunto_actual());
		log2.EscribirEnArchivo("("+dia_actual+")"+" "+betasoide.getPunto_actual());
		log3.EscribirEnArchivo("("+dia_actual+")"+" "+vulcano.getPunto_actual());
		
		if (todos_alineados())
		{
			if (esta_lloviendo)
			{
				esta_lloviendo = false;
			}
			status_text_dia_actual = "El sol y los planetas se alinearon, hay sequia";
			hay_que_conectar = true;
			setPeriodos_de_sequia(getPeriodos_de_sequia() + 1);
		}
		else if (planetas_alineados())
		{
			if (esta_lloviendo)
			{
				esta_lloviendo = false;
			}
			status_text_dia_actual = "Presion y temperatura optimas!";
			hay_que_conectar = true;
			periodos_ideales++;
		}
		else
		{
			//Forman un triangulo
			if (el_sol_esta_dentro()) 
			{
				if (!esta_lloviendo)
				{
					esta_lloviendo = true;
					setDia_maximo_perimetro_dia(dia_actual);
					maximo_perimetro_dia = perimetro_planetas();
					if (maximo_perimetro_global < maximo_perimetro_dia)
					{
						maximo_perimetro_global = maximo_perimetro_dia;
						dia_maximo_perimetro_global = dia_actual;
					}
					periodos_de_lluvia++;
					status_text_dia_actual = "Comenzo a llover!";
				}
				else
				{
					perimetro_actual_dia = perimetro_planetas();
					if (perimetro_actual_dia > maximo_perimetro_dia)
					{
						setDia_maximo_perimetro_dia(dia_actual);
						maximo_perimetro_dia = perimetro_actual_dia;
						if (maximo_perimetro_global < maximo_perimetro_dia)
						{
							maximo_perimetro_global = maximo_perimetro_dia;
							dia_maximo_perimetro_global = dia_actual;
						}
					}
					status_text_dia_actual = "Esta lloviendo!";
				}	
				hay_que_conectar = true;
			}
			else 
			{
				if (esta_lloviendo)
				{
					esta_lloviendo = false;
				}
				hay_que_conectar = false;
				status_text_dia_actual = "No pasa nada en particular";
			}
		}
		
		return puntos_actuales();
	}

	public int getPeriodos_de_sequia() {
		return periodos_de_sequia;
	}

	public void setPeriodos_de_sequia(int periodos_de_sequia) {
		this.periodos_de_sequia = periodos_de_sequia;
	}

	public int getDia_maximo_perimetro_dia() {
		return dia_maximo_perimetro_dia;
	}

	public void setDia_maximo_perimetro_dia(int dia_maximo_perimetro_dia) {
		this.dia_maximo_perimetro_dia = dia_maximo_perimetro_dia;
	}

	public int getCantidad_errados() {
		return cantidad_errados;
	}

	public void setCantidad_errados(int cantidad_errados) {
		this.cantidad_errados = cantidad_errados;
	}

	public double getMaximo_perimetro_global() {
		return maximo_perimetro_global;
	}

	public void setMaximo_perimetro_global(int maximo_perimetro_global) {
		this.maximo_perimetro_global = maximo_perimetro_global;
	}

	public int getDia_maximo_perimetro_global() {
		return dia_maximo_perimetro_global;
	}

	public void setDia_maximo_perimetro_global(int dia_maximo_perimetro_global) {
		this.dia_maximo_perimetro_global = dia_maximo_perimetro_global;
	}

	public int getPeriodos_de_lluvia() {
		return periodos_de_lluvia;
	}

	public void setPeriodos_de_lluvia(int periodos_de_lluvia) {
		this.periodos_de_lluvia = periodos_de_lluvia;
	}

	public int getPeriodos_ideales() {
		return periodos_ideales;
	}

	public void setPeriodos_ideales(int periodos_ideales) {
		this.periodos_ideales = periodos_ideales;
	}
	
}
