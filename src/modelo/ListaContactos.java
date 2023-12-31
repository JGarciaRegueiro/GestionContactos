package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaContactos {
	
	public static void guardarContactos(List<Contacto> listaContactos) {

		 try (BufferedWriter writer = new BufferedWriter(new FileWriter("contactos.txt"))) {		
			 for(Contacto contacto : listaContactos) {				

            	writer.write(contacto.getName() + "," + contacto.getPhone());
            	writer.newLine();
            }	 
        } catch (IOException e) {
            e.printStackTrace();
	    }
	   
	}
	
	public static List<Contacto> cargarContactos(){
        List<Contacto> contactos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("contactos.txt"))) {
            //Va a leer linea a linea convirtiendo el .txt en un formato para utilizar nosotros
        	String linea;
            
        	while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
               
                if (partes.length == 2) {
                    String name = partes[0];
                    String phone = partes[1];
                    
                    contactos.add(new Contacto(name, phone));
                }
            }

            System.out.println("Contactos cargados exitosamente desde contactos.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactos;
	}
}
