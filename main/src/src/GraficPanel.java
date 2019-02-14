import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

public class GraficPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point2D.Double puntof,puntob,puntov;
	private boolean conectar = false;
	
	public GraficPanel()
	{
		super();
		puntof = new Point2D.Double(0, 0);
		puntob = new Point2D.Double(0, 0);
		puntov = new Point2D.Double(0, 0);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.drawLine(getWidth()/2, 0, getWidth()/2, getWidth());
		g.drawLine(0, getWidth()/2, getWidth(), getWidth()/2);
		
		
		//g.drawLine(getWidth()/2, getHeight()/2, getWidth()/2, getHeight()/2);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawOval(getWidth()/2-25, getHeight()/2-25, 50, 50);
		g2d.drawOval(getWidth()/2-50, getHeight()/2-50, 100, 100);
		g2d.drawOval(getWidth()/2-100, getHeight()/2-100, 200, 200);
		
		g2d.setColor(Color.YELLOW);
		Ellipse2D.Double circle = new Ellipse2D.Double(getWidth()/2-5, getHeight()/2-5, 10, 10);
		g2d.fill(circle);
		g2d.draw(circle);
		
		//g2d.drawOval(getWidth()/2-25+(5.0*10), getHeight()/2-25+(-0.1*10), 10, 10);
		    
	    if ((puntof.getX() == 0 && puntof.getY() == 0) && (puntob.getX() == 0 && puntob.getY() == 0) && (puntov.getX() == 0 && puntov.getY() == 0))
	    {
	    	//System.out.println("No hay puntos que graficar");
	    }
	    else
	    {
	    	g.setColor(Color.RED);
	    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		    
		    double puntox5 = ((getWidth()/2)-5)+((puntof.getX())*5);
		    double puntox6 = ((getHeight()/2)-5)+((puntof.getY())*5);
		    
		    //Ellipse2D.Double shape = new Ellipse2D.Double(getWidth()/2-25+(5.0*10), getHeight()/2-25+(-0.1*10), 10, 10);
		    Ellipse2D.Double shape = new Ellipse2D.Double(puntox5, puntox6, 10, 10);
		    
		    double puntox1 = ((getWidth()/2)-5)+((puntob.getX())*5);
		    double puntox2 = ((getHeight()/2)-5)+((puntob.getY())*5);
		    
		    Ellipse2D.Double shape2 = new Ellipse2D.Double(puntox1, puntox2, 10, 10);
		    
		    //System.out.println("X = "+puntob.getX()+" , Y = " +puntob.getY()+", Punto 1 = "+puntox1+", Punto 2 =  "+puntox2);
		    
		    double puntox3 = ((getWidth()/2)-5)+((puntov.getX())*5);
		    double puntox4 = ((getHeight()/2)-5)+((puntov.getY())*5);
		    
		    
		    
		    Ellipse2D.Double shape3 = new Ellipse2D.Double(puntox3, puntox4, 10, 10);
		    
		    g2d.fill(shape);
		    g2d.fill(shape2);
		    g2d.fill(shape3);
		    
		    g2d.draw(shape);
		    g2d.draw(shape2);
		    g2d.draw(shape3);
		    
		    if (conectar)
		    {
		    	g.setColor(Color.BLACK);
		    	g2d.draw(new Line2D.Double(((getWidth()/2))+((puntob.getX())*5), ((getHeight()/2))+((puntob.getY())*5), ((getWidth()/2))+((puntov.getX())*5), ((getHeight()/2))+((puntov.getY())*5)));
		    	g2d.draw(new Line2D.Double(((getWidth()/2))+((puntov.getX())*5), ((getHeight()/2))+((puntov.getY())*5), ((getWidth()/2))+((puntof.getX())*5), ((getHeight()/2))+((puntof.getY())*5)));
		    	g2d.draw(new Line2D.Double(((getWidth()/2))+((puntob.getX())*5), ((getHeight()/2))+((puntob.getY())*5), ((getWidth()/2))+((puntof.getX())*5), ((getHeight()/2))+((puntof.getY())*5)));
		    }
	    }
		
        //g.drawLine(getWidth()/2, getHeight()/2, 22, getHeight()/2);
        //g.drawLine(getWidth()/2, getHeight()/2+10, 22, getHeight()/2+10);
        //g.drawLine(getWidth()/2, getHeight()/2+20, 22, getHeight()/2+20);
    }

	public Point2D.Double getPuntof() {
		return puntof;
	}

	public void setPuntof(Point2D.Double puntof) {
		this.puntof = puntof;
	}

	public Point2D.Double getPuntob() {
		return puntob;
	}

	public void setPuntob(Point2D.Double puntob) {
		this.puntob = puntob;
	}

	public Point2D.Double getPuntov() {
		return puntov;
	}

	public void setPuntov(Point2D.Double puntov) {
		this.puntov = puntov;
	}

	public boolean isConectar() {
		return conectar;
	}

	public void setConectar(boolean conectar) {
		this.conectar = conectar;
	}
	
	
}
