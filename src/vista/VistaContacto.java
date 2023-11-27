package vista;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.Controlador;
import modelo.Contacto;
import modelo.ListaContactos;

public class VistaContacto extends JDialog {
	
	JLabel labelName, labelPhone;
	JTextField fieldName, fieldPhone;
	JButton buttonOK, buttonCancel;
	JLabel wrongName, wrongPhone;
	
	Controlador controlador;
	
	public VistaContacto(Controlador controlador) {
		this.controlador=controlador;
		setSize(300, 300);		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle("Nuevo contacto");
		setLayout(null);
		init();
		setLocationRelativeTo(null);
		setVisible(true);
		
		addWindowListener(new WindowAdapter(){ 
			@Override
			public void windowClosing(WindowEvent e) {
			controlador.setButtonMainListeners(true);
				
			}
		});
	}
	
	private void init() {
		ImageIcon iconName = changeIcon(new ImageIcon("images/person.png"), Color.black, 50);
		ImageIcon iconPhone = changeIcon(new ImageIcon("images/phone.png"), Color.black, 50); 		
		
		labelName = new JLabel(iconName);
		labelName.setBounds(20, 20, 50, 50);
		add(labelName);

		fieldName = new JTextField();
		fieldName.setBounds(80, 30, getWidth() - 120, 30);
		add(fieldName);
		
		labelPhone = new JLabel(iconPhone);
		labelPhone.setBounds(20,100,50,50);
		add(labelPhone);
		
		fieldPhone = new JTextField("");
		fieldPhone.setBounds(80, 110, getWidth() - 120, 30);
		add(fieldPhone);
		
		buttonOK = new JButton("OK");
		buttonOK.setName("ok");
		buttonOK.setBounds(20, 200, 120, 50);
		add(buttonOK);
		
		buttonCancel = new JButton("Cancelar");
		buttonCancel.setName("cancelar");
		buttonCancel.setBounds(getWidth() - 120 - 20 - 10, 200, 120, 50);
		add(buttonCancel);
		
		buttonOK.addActionListener(controlador);
		buttonCancel.addActionListener(controlador);
		
	}
	
	//Getters & Setters
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
		return buttonCancel;
	}

	public void setButtonCancel(JButton buttonCancel) {
		this.buttonCancel = buttonCancel;
	}

	
	public static ImageIcon changeIcon(ImageIcon iconoOriginal, Color colorNuevo, int size) {
        // Crear una imagen con el mismo tamaño que el icono original
		iconoOriginal = new ImageIcon(iconoOriginal.getImage().getScaledInstance(size, size, 500));	
		
        BufferedImage imagenModificada = new BufferedImage(
                iconoOriginal.getIconWidth(),
                iconoOriginal.getIconHeight(),
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
