package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import vista.VistaContacto;

public class ControladorContacto implements ActionListener {

	VistaContacto vista;
	Controlador controlador;
	
	String nombre;
	String telefono;
	
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
	

}
