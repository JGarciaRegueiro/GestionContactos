package vista;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import controlador.Controlador;
import controlador.Main;
import modelo.Contacto;
import modelo.ListaContactos;

public class Vista extends JFrame{

	JLabel titleLabel; 
	JButton buttonAdd, buttonDelete, buttonEdit;
	JTable table;
	DefaultTableModel tableModel;
	JScrollPane scrollPane;
	List<Contacto> listaContactos = new ArrayList<>();
	

	
	
	public Vista() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		
		setTitle("Mis contactos");
		setSize(400, 700);
		//getContentPane().setBackground(Color.white);
		
		init();
		
		setLocationRelativeTo(null);
		
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
		
		setVisible(true);
	}
	
	private void init() {	
		System.out.println("w: " + getWidth());
		System.out.println("h: " + getHeight());
				
		titleLabel = new JLabel("Mis Contactos");
		titleLabel.setForeground(Color.black);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 45));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 0, getWidth() - 10, 50);
		add(titleLabel);
		
		Color cButton = Color.black;
		int sizeBtn = 65;
		
		buttonAdd = createButton("añadir", "images/person_add.png", cButton, sizeBtn);
		buttonAdd.setBounds(40, 550, 75, 75);		
		add(buttonAdd);

		buttonDelete = createButton("eliminar", "images/person_remove.png", cButton, sizeBtn);
		buttonDelete.setBounds(155, 550, 75, 75);
		add(buttonDelete);

		buttonEdit = createButton("editar", "images/edit.png", cButton, sizeBtn);
		buttonEdit.setBounds(265, 550, 75, 75);
		add(buttonEdit);

		String[] columnName = { "Nombre", "Teléfono" };
		tableModel = new DefaultTableModel(columnName, 0) {
			
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		table = new JTable(tableModel);
			
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(40, 60, getWidth()-90, 450);
		add(scrollPane);		
	
		GradientPanel gradientPanel = new GradientPanel(GradientPanel.edixC1, GradientPanel.edixC2,.3f);		
		gradientPanel.setSize(getWidth(), getHeight());
		add(gradientPanel);

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
	
	private JButton createButton(String id, String path, Color color, int size) {
		ImageIcon icon = new ImageIcon(path);		
		icon = new ImageIcon(icon.getImage().getScaledInstance(size, size, 500));		
		
		JButton button = new JButton(changeColorIcon(icon, color));		
		
		button.setName(id);
		
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		return button;
	}
	
	
	public static ImageIcon changeColorIcon(ImageIcon iconoOriginal, Color colorNuevo) {
        // Crear una imagen con el mismo tamaño que el icono original
		
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

	public void disableButtons() {
		buttonAdd.setEnabled(false);
		buttonEdit.setEnabled(false);
		buttonDelete.setEnabled(false);
	}
	
	public void enableButtons() {
		buttonAdd.setEnabled(true);
		buttonEdit.setEnabled(true);
		buttonDelete.setEnabled(true);
	}
}
