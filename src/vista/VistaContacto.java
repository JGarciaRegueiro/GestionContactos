package vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.Controlador;

public class VistaContacto extends JDialog {
	
	JLabel labelName, labelPhone;
	JTextField fieldName, fieldPhone;
	JButton buttonOK, buttonCancel;
	Controlador controlador;
	
	public VistaContacto(Controlador controlador) {
		this.controlador=controlador;
		setBounds(100,100,500,500);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle("Nuevo contacto");
		setLayout(null);
		init();
		setVisible(true);
	}
	
	private void init() {
		labelName = new JLabel("Nombre");
		labelName.setBounds(100,100,100,40);
		add(labelName);
		
		fieldName = new JTextField("");
		fieldName.setBounds(300,100,100,40);
		add(fieldName);
		
		labelPhone = new JLabel("Teléfono");
		labelPhone.setBounds(100,200,100,40);
		add(labelPhone);
		
		fieldPhone = new JTextField("");
		fieldPhone.setBounds(300,200,100,40);
		add(fieldPhone);
		
		buttonOK = new JButton("OK");
		buttonOK.setBounds(100,300,100,40);
		add(buttonOK);
		
		buttonCancel = new JButton("Cancelar");
		buttonCancel.setBounds(250,300,100,40);
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
	

}
