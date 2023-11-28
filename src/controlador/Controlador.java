package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelo.Contacto;
import vista.Vista;
import vista.VistaContacto;

public class Controlador implements ActionListener {

	Vista vista;
	VistaContacto vistaContacto;
	Contacto contacto = new Contacto("","");
	boolean editMode = false;
	int row;
	
	public Controlador(Vista vista) {
		this.vista=vista;
	}
	
	public Controlador(VistaContacto vistaContacto) {
		this.vistaContacto=vistaContacto;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
		//String selectedButton = button.getText();
		String idBtn = button.getName();
		
		System.out.println("Boton presionado: " + button.getName());
		
		switch(idBtn) {
			case "añadir":
				vista.disableButtons();
				vistaContacto = new VistaContacto(this, "limpiar");
				break;				
			case "editar":
				if (vista.getTable().getSelectedRow()!=-1) {
					vista.disableButtons();
					editMode=true;
					vistaContacto = new VistaContacto(this, "recuperar");
					vistaContacto.setTitle("Editar contacto");
					row = vista.getTable().getSelectedRow();
					
					String nam = (String) vista.getTable().getValueAt(row,0);
					String ph = (String) vista.getTable().getValueAt(row,1);
					
					vistaContacto.getFieldName().setText(nam);
					vistaContacto.getFieldPhone().setText(ph);					
					vistaContacto.getControladorContacto().setNombre(nam);
					vistaContacto.getControladorContacto().setTelefono(ph);
					
				}else {
					JOptionPane.showMessageDialog(null, "Selecciona un contacto", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				
			case "eliminar":
				if (vista.getTable().getSelectedRow()!=-1) {
					vista.getTableModel().removeRow(vista.getTable().getSelectedRow());
				}else {
					JOptionPane.showMessageDialog(null, "Selecciona un contacto", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				
			case "ok":
				setOK();
				break;
				
			case "cancelar":
				vista.enableButtons();
				vistaContacto.dispose();
				break;
		}
		
	}
	
	private boolean validationName(String name) {
		if (name.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		return false;
	}
	
	private boolean validationPhone(String phone) {
		String regex = "\\d{9}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phone);
		if (!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "El formato del número no es correcto,\n debe contener 9 dígitos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} 
		return false;
	}
	
	public void setButtonMainListeners(boolean value) {
		if(value) vista.enableButtons();
		else vista.disableButtons();
	}

	public void setOK() {
		vista.enableButtons();
		if (!editMode) {
			if (!validationName(vistaContacto.getFieldName().getText()) && !validationPhone(vistaContacto.getFieldPhone().getText())) {
				vista.getTableModel().addRow(new String[] {vistaContacto.getFieldName().getText(), vistaContacto.getFieldPhone().getText()});
				vistaContacto.dispose();
				editMode=false;
			}
		}else {
			if (!validationName(vistaContacto.getFieldName().getText()) && !validationPhone(vistaContacto.getFieldPhone().getText())) {
				vista.getTable().setValueAt(vistaContacto.getFieldName().getText(),row,0);
				vista.getTable().setValueAt(vistaContacto.getFieldPhone().getText(),row,1);
				vistaContacto.dispose();
				editMode=false;
			}
		}
	}

	
}
