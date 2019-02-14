import java.awt.EventQueue;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.swing.JFrame;
import funciones.universo;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class PantallaPrincipal {

	private JFrame frame;
	private static universo nuevo_universo;
	private static GraficPanel panel;
	private static JLabel label; 
	private static JLabel lblPeriodosDeLluvia;
	private static JLabel label_periodos_lluvia;
	private static JLabel lblPeriodosDeSequia;
	private static JLabel label_periodos_sequia;
	private static JLabel lblPeriodosDeTemperatura;
	private static JLabel label_periodos_ideales;
	private static JLabel lblNewLabel_1;
	private static JLabel label_maxima_intensidad;
	private JButton btnReiniciarSimulacion;
	private static JTextArea textArea_status;
	private JLabel lblCalculando;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		if (args.length == 0)
		{
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						nuevo_universo = new universo();
						PantallaPrincipal window = new PantallaPrincipal();
						window.frame.setVisible(true);
						//start_countdown();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		else
		{
			if (isInteger(args[0]))
			{
				try {
					int result = Integer.parseInt(args[0]);
					nuevo_universo = new universo();
					if (result >0)
					{
						nuevo_universo.operacion_automatica(result);
						System.out.println("Se ha completado la operacion.");
						System.out.println("Han transcurrido "+result+" dias en el universo.");
						System.out.println("Han ocurrido "+nuevo_universo.getPeriodos_de_lluvia()+" periodos de lluvia");
						System.out.println("Han ocurrido "+nuevo_universo.getPeriodos_de_sequia()+" periodos de sequia");
						System.out.println("Han ocurrido "+nuevo_universo.getPeriodos_ideales()+" periodos de temperatura y presion ideal");
						System.out.println("El dia en que la precipitacion fue mas fuerte fue el dia "+nuevo_universo.getDia_maximo_perimetro_global());
					}
					else
					{
						System.out.println("El argumento ingresado no es un numero entero valido.");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("El argumento ingresado no es un numero entero valido.");
			}
		}
	}
	
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}

	/**
	 * Create the application.
	 */
	public PantallaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 893, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new GraficPanel();
		panel.setBounds(394, 57, 473, 473);
		frame.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Avanzar 1 D\u00EDa");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblCalculando.setVisible(true);
				start_cont_2();
				lblCalculando.setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 292, 103, 32);
		frame.getContentPane().add(btnNewButton);
		
		label = new JLabel("0");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(319, 70, 46, 29);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("Simulaci\u00F3n de Universo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(22, 30, 343, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblDiaActual = new JLabel("Dia actual");
		lblDiaActual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDiaActual.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaActual.setBounds(10, 70, 91, 29);
		frame.getContentPane().add(lblDiaActual);
		
		lblPeriodosDeLluvia = new JLabel("Periodos de Lluvia");
		lblPeriodosDeLluvia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPeriodosDeLluvia.setBounds(10, 110, 124, 45);
		frame.getContentPane().add(lblPeriodosDeLluvia);
		
		label_periodos_lluvia = new JLabel("0");
		label_periodos_lluvia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_periodos_lluvia.setBounds(319, 125, 46, 14);
		frame.getContentPane().add(label_periodos_lluvia);
		
		lblPeriodosDeSequia = new JLabel("Periodos de Sequia");
		lblPeriodosDeSequia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPeriodosDeSequia.setBounds(10, 195, 137, 19);
		frame.getContentPane().add(lblPeriodosDeSequia);
		
		label_periodos_sequia = new JLabel("0");
		label_periodos_sequia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_periodos_sequia.setBounds(319, 197, 46, 14);
		frame.getContentPane().add(label_periodos_sequia);
		
		lblPeriodosDeTemperatura = new JLabel("Periodos de temperatura y presion ideales");
		lblPeriodosDeTemperatura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPeriodosDeTemperatura.setBounds(10, 229, 279, 45);
		frame.getContentPane().add(lblPeriodosDeTemperatura);
		
		label_periodos_ideales = new JLabel("0");
		label_periodos_ideales.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_periodos_ideales.setBounds(319, 244, 46, 14);
		frame.getContentPane().add(label_periodos_ideales);
		
		lblNewLabel_1 = new JLabel("Dia de Maxima intensidad");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 154, 222, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		label_maxima_intensidad = new JLabel("0");
		label_maxima_intensidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_maxima_intensidad.setBounds(319, 164, 46, 14);
		frame.getContentPane().add(label_maxima_intensidad);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 285, 355, 14);
		frame.getContentPane().add(separator);
		
		JButton btnAvanzar5Dias = new JButton("Avanzar 5 dias");
		btnAvanzar5Dias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblCalculando.setVisible(true);
				for (int i = 0; i < 5; i++)
				{
					start_cont_2();
				}
				lblCalculando.setVisible(false);
			}
		});
		btnAvanzar5Dias.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAvanzar5Dias.setBounds(123, 292, 109, 32);
		frame.getContentPane().add(btnAvanzar5Dias);
		
		JButton btnAvanzar10Dias = new JButton("Avanzar 10 dias");
		btnAvanzar10Dias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCalculando.setVisible(true);
				for (int i = 0; i < 10; i++)
				{
					start_cont_2();
				}
				lblCalculando.setVisible(false);
			}
		});
		btnAvanzar10Dias.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAvanzar10Dias.setBounds(242, 292, 114, 33);
		frame.getContentPane().add(btnAvanzar10Dias);
		
		JButton btnAvanzar1Ao = new JButton("Avanzar 1 a\u00F1o");
		btnAvanzar1Ao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCalculando.setVisible(true);
				for (int i = 0; i < 360; i++)
				{
					start_cont_2();
				}
				lblCalculando.setVisible(false);
			}
		});
		btnAvanzar1Ao.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAvanzar1Ao.setBounds(10, 328, 103, 32);
		frame.getContentPane().add(btnAvanzar1Ao);
		
		JButton btnAvanzar5Aos = new JButton("Avanzar 5 a\u00F1os");
		btnAvanzar5Aos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCalculando.setVisible(true);
				for (int i = 0; i < 1800; i++)
				{
					start_cont_2();
				}
				lblCalculando.setVisible(false);
			}
		});
		btnAvanzar5Aos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAvanzar5Aos.setBounds(123, 327, 109, 34);
		frame.getContentPane().add(btnAvanzar5Aos);
		
		JButton btnAvanzar10Aos = new JButton("Avanzar 10 a\u00F1os");
		btnAvanzar10Aos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCalculando.setVisible(true);
				for (int i = 0; i < 3600; i++)
				{
					start_cont_2();
				}
				lblCalculando.setVisible(false);
			}
		});
		btnAvanzar10Aos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAvanzar10Aos.setBounds(242, 327, 114, 34);
		frame.getContentPane().add(btnAvanzar10Aos);
		
		btnReiniciarSimulacion = new JButton("Reiniciar Simulacion");
		btnReiniciarSimulacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCalculando.setVisible(true);
				try {
					nuevo_universo = new universo();
					textArea_status.setText("");
					//setear_etiquetas();
					panel.setPuntof(nuevo_universo.getFerengi().getPunto_actual());
					panel.setPuntob(nuevo_universo.getBetasoide().getPunto_actual());
					panel.setPuntov(nuevo_universo.getVulcano().getPunto_actual());
					panel.setConectar(false);
					panel.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblCalculando.setVisible(false);
				
			}
		});
		btnReiniciarSimulacion.setBounds(697, 11, 170, 23);
		frame.getContentPane().add(btnReiniciarSimulacion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 395, 343, 135);
		frame.getContentPane().add(scrollPane);
		
		textArea_status = new JTextArea();
		scrollPane.setViewportView(textArea_status);
		textArea_status.setEnabled(false);
		textArea_status.setEditable(false);
		
		lblCalculando = new JLabel("Calculando...");
		lblCalculando.setVisible(false);
		lblCalculando.setBounds(10, 370, 91, 14);
		frame.getContentPane().add(lblCalculando);
		
			
	}
	
	@SuppressWarnings("unused")
	private static void start_countdown()
	{
		Point2D.Double[] arreglo_puntos = {};
		for (int i = 0;i<3560;i++ )
		{
			arreglo_puntos = nuevo_universo.operacion_automatica_2();
			panel.setPuntof(arreglo_puntos[0]);
			panel.setPuntob(arreglo_puntos[1]);
			panel.setPuntov(arreglo_puntos[2]);
			panel.repaint();
			/*try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}	
	}
	
	private static void start_cont_2()
	{
		Point2D.Double[] arreglo_puntos = {};
		arreglo_puntos = nuevo_universo.operacion_automatica_2();
		//label.setText(""+nuevo_universo.getDiaActual());
		setear_etiquetas();
		panel.setPuntof(arreglo_puntos[0]);
		panel.setPuntob(arreglo_puntos[1]);
		panel.setPuntov(arreglo_puntos[2]);
		panel.setConectar(nuevo_universo.isHay_que_conectar());
		panel.repaint();	
	}
	
	private static void setear_etiquetas()
	{
		//lblPeriodosDeLluvia.setText(""+nuevo_universo.getPeriodos_de_lluvia());
		label_periodos_lluvia.setText(""+nuevo_universo.getPeriodos_de_lluvia());
		//lblPeriodosDeSequia.setText("");
		label_periodos_sequia.setText(""+nuevo_universo.getPeriodos_de_sequia());
		//lblPeriodosDeTemperatura.setText("");
		label_periodos_ideales.setText(""+nuevo_universo.getPeriodos_ideales());
		label.setText(""+nuevo_universo.getDiaActual());
		label_maxima_intensidad.setText(""+nuevo_universo.getDia_maximo_perimetro_global());
		if (textArea_status.getText()=="") textArea_status.setText(nuevo_universo.getStatus_text_dia_actual());
		else textArea_status.setText(textArea_status.getText()+"\r\n"+nuevo_universo.getStatus_text_dia_actual());
	}
}
