package org.pr2.dominio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class Libreta {
    private String nombreFichero = "contactos.txt";
    private ArrayList<Contacto> contactos = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Contacto contacto : contactos) {
            sb.append(contacto).append("\n");
        }
        return sb.toString();
    }

    public Libreta() {
        try {
            File fichero = new File(nombreFichero);
            if (!fichero.exists()) {
                fichero.createNewFile();
            }
            Scanner sc = new Scanner(fichero);
            while (sc.hasNextLine()) {
                String nombre = sc.nextLine();
                String telefono = sc.nextLine();
                Contacto contacto = new Contacto(nombre, telefono);
                contactos.add(contacto);
            }
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void volcarContactos() {
        try {
            FileWriter fw = new FileWriter(nombreFichero);
            for (Contacto contacto : contactos) {
                fw.write(contacto.getNombre() + "\n");
                fw.write(contacto.getTelefono() + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void annadirContacto(String nombre, String telefono) {
        Contacto contacto = new Contacto(nombre, telefono);
        contactos.add(contacto);
        this.volcarContactos();
    }

    public void borrarContacto(String nombre) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equals(nombre)) {
                contactos.remove(contacto);
                break;
            }
        }
        this.volcarContactos();
    }

    public void generarHojaDeCalculo() {
        final Object[][] datos = new Object[contactos.size()][2];
        int i = 0;
        for (Contacto contacto : contactos) {
            datos[i][0] = contacto.getNombre();
            datos[i][1] = contacto.getTelefono();
            i++;
        }
        String[] columnas = new String[] { "Nombre", "Teléfono" };
        TableModel modelo = new DefaultTableModel(datos, columnas);
        final File file = new File("output/contactos.ods");
        try {
            SpreadSheet.createEmpty(modelo).saveAs(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
