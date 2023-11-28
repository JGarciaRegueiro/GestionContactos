package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vista.VistaContacto;

public class ControladorContacto implements ActionListener {

	VistaContacto vista;
	Controlador controlador;
	
	String nombre;
	String telefono;

	boolean firstError;
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public ControladorContacto(VistaContacto vista, Controlador controlador) {
		this.vista = vista;
		this.controlador = controlador;
		
		vista.addWindowListener(new WindowAdapter(){ 
			@Override
			public void windowClosing(WindowEvent e) {
			controlador.setButtonMainListeners(true);
				
			}
		});
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
		//String selectedButton = button.getText();
		String idBtn = button.getName();
		
		System.out.println("Boton presionado: " + button.getName());
		
		switch (idBtn) {
		case "ok": controlador.setOK();
			break;
		case "recuperar": setRecuperar();
			break;
		case "limpiar": setLimpiar();
			break;
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + idBtn);
		}
		
	}	
	
	public void setLimpiar() {
		vista.getFieldName().setText("");
		vista.getFieldPhone().setText("");
	}
	
	public void setRecuperar() {
		vista.getFieldName().setText(nombre);
		vista.getFieldPhone().setText(telefono);
	}
	
	//Efectos	
	public DocumentListener getDocumentListener(JTextField field, JTextArea aux) {
		return new DocumentListener() {
			
		
			@Override
			public void removeUpdate(DocumentEvent e) {
				handleValidations(field, aux, 2);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				handleValidations(field, aux, 2);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("changed: " + field.getName());
			}
		};
	}
	
	private void handleValidations(JTextField field, JTextArea aux, int sBorder) {		
		
		switch (field.getName()) {
			case "name": 
					if(validationName(field.getText())) {
						updateColor(field, Color.green, sBorder);
						aux.setText("");
					}
						
					else {
						updateColor(field, Color.red, sBorder);
						aux.setText("El formato del nombre no es correcto, debe tener al menos 2 caracteres");
					}
				break;
			case "phone":
				if(validationPhone(field.getText())) {
					updateColor(field, Color.green, sBorder);
					aux.setText("");
				}
					
				else {					
					updateColor(field, Color.red, sBorder);
					aux.setText("El formato del número no es correcto, debe contener 9 dígitos");
				}
				break;
			
		}
	}

	private boolean validationName(String name) {
		if (name.equalsIgnoreCase("") || name.length() < 2) {
			
			
			return false;
		}
		else return true;
	}
	
	private boolean validationPhone(String phone) {
		String regex = "\\d{9}";		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phone);
		
		if (!matcher.matches())		
			return false;		 
		else return true;
	}
	

	private void updateColor(JTextField field, Color c, int borderSize) {
		
		Border b = BorderFactory.createLineBorder(c, borderSize);		
		field.setBorder(b);
	}
	
	
}
