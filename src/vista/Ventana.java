package vista;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import controlador.Controlador;

public abstract class Ventana extends JFrame{

	public Ventana(String title, int w, int h, Ventana reference, Controlador controlador) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(w, h);
		setResizable(false);
		
		setTitle(title);
		setLayout(null);
		
		getContentPane().setBackground(Color.white);
		setLocationRelativeTo(reference);
		
		init();		
		initListeners(controlador);
	}
	
	protected abstract void init();
	protected abstract void initListeners(Controlador controlador);
	
	
}
