package controlador;

import java.util.List;

import modelo.Contacto;
import modelo.ListaContactos;
import vista.Vista;

public class Main {

	public static void main(String[] args) {
		
		Vista vista = new Vista();
		Controlador controlador = new Controlador(vista);
		vista.initListeners(controlador);
		List<Contacto> listaContactos = ListaContactos.cargarContactos();
		for (Contacto contacto : listaContactos) {
			vista.getTableModel().addRow(new String[] {contacto.getName(),contacto.getPhone()});
		}
	}

}
