package vista;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import controlador.Controlador;
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
		ImageIcon icon = new ImageIcon(getClass().getResource("/contacts.png"));
        setIconImage(icon.getImage());
		
		init();
		
		setLocationRelativeTo(null);
		
		//aquí el control de cierre para guardar los datos
		addWindowListener(new WindowAdapter(){ 
			@Override
			public void windowClosing(WindowEvent e) {
				for (int row = 0; row < tableModel.getRowCount(); row++) {
				        String name = (String) tableModel.getValueAt(row,0);
				        String phone = (String) tableModel.getValueAt(row,1);
				        Contacto contacto = new Contacto(name,phone);
				        listaContactos.add(contacto);
				        System.out.print(contacto + "\t"); // Acción con el valor de la celda (en este caso, imprimirlo)
				    System.out.println(); // Salto de línea al final de cada fila
				}
				ListaContactos.guardarContactos(listaContactos);
			}
		});
		
		setVisible(true);
	}
	
	
	private void init() {	
		
		Font googleFont = null;
		Font lobsterFont = null;

		try {
		    InputStream googleFontStream = getClass().getResourceAsStream("/google.ttf");
		    InputStream lobsterFontStream = getClass().getResourceAsStream("/lobster.ttf");

		    googleFont = Font.createFont(Font.TRUETYPE_FONT, googleFontStream);
		    lobsterFont = Font.createFont(Font.TRUETYPE_FONT, lobsterFontStream);
		} catch (FontFormatException | IOException e) {
		    e.printStackTrace();
		}
		

		titleLabel = new JLabel("Mis Contactos");
		titleLabel.setForeground(Color.white);
	    titleLabel.setFont(googleFont.deriveFont(Font.PLAIN, 40));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 30, getWidth() - 10, 50);
		add(titleLabel);
		
		Color cButton = Color.black;
		int sizeBtn = 65;
		
		buttonAdd = createButton("añadir", "person_add.png", cButton, sizeBtn);
		buttonAdd.setBounds(40, 550, 75, 75);		
		add(buttonAdd);

		buttonDelete = createButton("eliminar", "person_remove.png", cButton, sizeBtn);
		buttonDelete.setBounds(155, 550, 75, 75);
		add(buttonDelete);

		buttonEdit = createButton("editar", "edit.png", cButton, sizeBtn);
		buttonEdit.setBounds(265, 550, 75, 75);
		add(buttonEdit);

		String[] columnName = { "Nombre", "Teléfono" };
		tableModel = new DefaultTableModel(columnName, 0) {
			
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		table = new JTable(tableModel);
		table.getTableHeader().setFont(lobsterFont.deriveFont(Font.PLAIN, 15));
			
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(40, 90, getWidth()-90, 450);;
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
        try {
            // Obtener la URL de la imagen desde el ClassLoader
            ClassLoader classLoader = getClass().getClassLoader();
            URL imageUrl = classLoader.getResource(path);

            // Cargar la imagen desde la URL
            BufferedImage imagenOriginal = ImageIO.read(imageUrl);

            // Escalar la imagen al tamaño deseado
            Image imagenEscalada = imagenOriginal.getScaledInstance(size, size, Image.SCALE_SMOOTH);

            // Crear un ImageIcon con la imagen escalada
            ImageIcon icon = new ImageIcon(imagenEscalada);

            // Cambiar el color del icono
            icon = changeColorIcon(icon, color);

            // Crear y configurar el botón
            JButton button = new JButton(icon);
            button.setName(id);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);

            return button;
        } catch (IllegalArgumentException iae) {
			// TODO: handle exception
        	System.err.println("No se pudo cargar la imagen: " + path);
        	return null;
		} 
        
        catch (IOException e) {
            e.printStackTrace();
            return null; // Manejar el error según tus necesidades
        }
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
