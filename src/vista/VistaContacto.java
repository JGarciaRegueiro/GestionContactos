package vista;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controlador.Controlador;
import controlador.ControladorContacto;

public class VistaContacto extends JDialog {

	JLabel labelName, labelPhone;
	JTextField fieldName, fieldPhone;
	JButton buttonOK, buttonSecondary;
	JTextArea wrongName, wrongPhone;

	Controlador controlador;
	ControladorContacto controladorContacto;
	String secondAction;

	public ControladorContacto getControladorContacto() {
		return controladorContacto;
	}

	public VistaContacto(Controlador controlador, String action) {
		setTitle("Nuevo contacto");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(null);
		setSize(300, 300);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\contacts.png"));

		this.controlador = controlador;
		controladorContacto = new ControladorContacto(this, controlador);

		setLocationRelativeTo(null);

		secondAction = action;
		init();

		setVisible(true);
	}

	// Getters & Setters
	public JLabel getLabelName() {
		return labelName;
	}

	public void setLabelName(JLabel labelName) {
		this.labelName = labelName;
	}

	public JLabel getLabelPhone() {
		return labelPhone;
	}

	public void setLabelPhone(JLabel labelPhone) {
		this.labelPhone = labelPhone;
	}

	public JTextField getFieldName() {
		return fieldName;
	}

	public void setFieldName(JTextField fieldName) {
		this.fieldName = fieldName;
	}

	public JTextField getFieldPhone() {
		return fieldPhone;
	}

	public void setFieldPhone(JTextField fieldPhone) {
		this.fieldPhone = fieldPhone;
	}

	public JButton getButtonOK() {
		return buttonOK;
	}

	public void setButtonOK(JButton buttonOK) {
		this.buttonOK = buttonOK;
	}

	public JButton getButtonCancel() {
		return buttonSecondary;
	}

	public void setButtonCancel(JButton buttonCancel) {
		this.buttonSecondary = buttonCancel;
	}

	public JTextArea getWrongName() {
		return wrongName;
	}

	public JTextArea getWrongPhone() {
		return wrongPhone;
	}

	private void init() {
		
		ImageIcon iconName = changeIcon(loadIcon("person.png"), Color.black, 50);
		ImageIcon iconPhone = changeIcon(loadIcon("phone.png"), Color.black, 50);

		labelName = new JLabel(iconName);
		labelName.setBounds(20, 20, 50, 50);
		add(labelName);

		fieldName = new JTextField();
		fieldName.setBounds(80, 30, getWidth() - 120, 30);
		add(fieldName);
		
		wrongName = buildWrongTextArea("");
		wrongName.setBounds(80, 60, 180, 40);
		add(wrongName);
		
		labelPhone = new JLabel(iconPhone);
		labelPhone.setBounds(20, 100, 50, 50);
		add(labelPhone);

		fieldPhone = new JTextField("");
		fieldPhone.setBounds(80, 110, getWidth() - 120, 30);
		add(fieldPhone);

		wrongPhone = buildWrongTextArea("");
		wrongPhone.setBounds(80, 140, 180, 40);
		add(wrongPhone);
		
		buttonOK = new JButton("OK");
		buttonOK.setName("ok");
		buttonOK.setBounds(20, 200, 120, 50);
		add(buttonOK);

		buttonSecondary = new JButton(secondAction.substring(0, 1).toUpperCase() + secondAction.substring(1));
		buttonSecondary.setName(secondAction);
		buttonSecondary.setBounds(getWidth() - 120 - 20 - 10, 200, 120, 50);
		add(buttonSecondary);

		//GradientPanel gradientPanel = new GradientPanel(GradientPanel.edixC2, GradientPanel.edixC1, .3f);
		//gradientPanel.setSize(getWidth(), getHeight());
		//add(gradientPanel);

		buttonOK.addActionListener(controladorContacto);
		buttonSecondary.addActionListener(controladorContacto);

		// Pruebas
		int borderSize = 3;
		fieldName.setName("name");
		fieldPhone.setName("phone");
		fieldName.getDocument().addDocumentListener(
				controladorContacto.getDocumentListener(fieldName, wrongName));
		fieldPhone.getDocument().addDocumentListener(
				controladorContacto.getDocumentListener(fieldPhone, wrongPhone));
	}
	
	private ImageIcon loadIcon(String filename) {
	    try {
	        // Obtener la URL de la imagen desde el ClassLoader
	        ClassLoader classLoader = getClass().getClassLoader();
	        URL imageUrl = classLoader.getResource(filename);

	        if (imageUrl != null) {
	            // Cargar la imagen desde la URL
	            Image image = Toolkit.getDefaultToolkit().getImage(imageUrl);

	            // Devolver un ImageIcon creado con la imagen
	            return new ImageIcon(image);
	        } else {
	            System.err.println("No se pudo cargar la imagen: " + filename);
	            // Manejar el error según tus necesidades
	            return null;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	private JTextArea buildWrongTextArea(String str) {
		JTextArea j = new JTextArea(str);	
		
		j.setForeground(Color.red);
		j.setFont(new Font("SansSerif", Font.PLAIN, 10));

		j.setLineWrap(true);
		j.setWrapStyleWord(true);		
		j.setBackground(null);
		
		return j;
	}
	
	public static ImageIcon changeIcon(ImageIcon iconoOriginal, Color colorNuevo, int size) {
		// Crear una imagen con el mismo tamaño que el icono original
		iconoOriginal = new ImageIcon(iconoOriginal.getImage().getScaledInstance(size, size, 500));

		BufferedImage imagenModificada = new BufferedImage(iconoOriginal.getIconWidth(), iconoOriginal.getIconHeight(),
				BufferedImage.TYPE_INT_ARGB);

		// Obtener el contexto gráfico de la imagen
		Graphics2D g = imagenModificada.createGraphics();

		// Dibujar el icono original en la imagen con el nuevo color
		g.drawImage(iconoOriginal.getImage(), 0, 0, null);
		g.setComposite(AlphaComposite.SrcAtop);
		g.setColor(colorNuevo);
		g.fillRect(0, 0, iconoOriginal.getIconWidth(), iconoOriginal.getIconHeight());

		// Liberar recursos
		g.dispose();

		// Crear y devolver un nuevo ImageIcon con la imagen modificada
		return new ImageIcon(imagenModificada);
	}
}
