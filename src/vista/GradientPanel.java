package vista;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GradientPanel extends JPanel{

	Color colorStart = Color.gray;
	Color colorEnd = Color.cyan;
	
	public final static Color edixC1 = new Color(106, 0, 194);
	public final static Color edixC2 = new Color(0, 204, 255);
	
	float percentageGradiant;
	
	public GradientPanel(Color a, Color b, float pg) {		
		colorStart = a;
		colorEnd = b;
		percentageGradiant = pg;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;

		
		GradientPaint gp = new GradientPaint(
				0, getHeight() * percentageGradiant, colorStart, 
				0, getHeight(), colorEnd);
		g2d.setPaint(gp);
		
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
	
}
