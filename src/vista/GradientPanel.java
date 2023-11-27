package vista;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GradientPanel extends JPanel{

	Color colorStart = Color.gray;
	Color colorEnd = Color.cyan;
	
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
				getWidth(), getHeight() * percentageGradiant, colorStart, 
				getWidth(), getHeight(), colorEnd);
		g2d.setPaint(gp);
		
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
	
}
