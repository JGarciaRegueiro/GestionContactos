package vista;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Contacto;
import modelo.ListaContactos;

public class Vista extends JFrame{

	JButton buttonAdd, buttonDelete, buttonEdit;
	JTable table;
	DefaultTableModel tableModel;
	JScrollPane scrollPane;
	List<Contacto> listaContactos = new ArrayList<>();
	
	public Vista() {
		setBounds(100,100,790,790);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Mis contactos");
		setLayout(null);
		getContentPane().setBackground(Color.white);
		init();
		setVisible(true);
		
		//Dejo aquí el control de cierre para guardar los datos
		addWindowListener(new WindowAdapter(){ 
			@Override
			public void windowClosing(WindowEvent e) {
				for (int row = 0; row < tableModel.getRowCount(); row++) {
				        String name = (String) tableModel.getValueAt(row,0);
				        String phone = (String) tableModel.getValueAt(row,1);
				        Contacto contacto = new Contacto(name,phone);
				        listaContactos.add(contacto);
				        System.out.print(contacto + "\t"); // Haces algo con el valor de la celda (en este caso, imprimirlo)
				    System.out.println(); // Salto de línea al final de cada fila
				}
				ListaContactos.guardarContactos(listaContactos);
			}
		});
		
	}
	
	private void init() {
		
		buttonAdd = new JButton("Añadir");
		buttonAdd.setBounds(100, 650, 150, 40);
		add(buttonAdd);
		
		buttonDelete = new JButton("Eliminar");
		buttonDelete.setBounds(300, 650, 150, 40);
		add(buttonDelete);
		
		buttonEdit = new JButton("Editar");
		buttonEdit.setBounds(500, 650, 150, 40);
		add(buttonEdit);
		
		String[] columnName = {"Nombre","Teléfono"};
		tableModel = new DefaultTableModel(columnName,0);
		
		table = new JTable(tableModel);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(100,50,550,550);
		add(scrollPane);
		
	}
	
	public void initListeners(Controlador controlador) {
		buttonAdd.addActionListener(controlador);
		buttonDelete.addActionListener(controlador);
		buttonEdit.addActionListener(controlador);
	}
	
	//Getters & Setters
	public JButton getButtonAdd() {
		return buttonAdd;
	}

	public void setButtonAdd(JButton buttonAdd) {
		this.buttonAdd = buttonAdd;
	}

	public JButton getButtonDelete() {
		return buttonDelete;
	}

	public void setButtonDelete(JButton buttonDelete) {
		this.buttonDelete = buttonDelete;
	}

	public JButton getButtonEdit() {
		return buttonEdit;
	}

	public void setButtonEdit(JButton buttonEdit) {
		this.buttonEdit = buttonEdit;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
}
