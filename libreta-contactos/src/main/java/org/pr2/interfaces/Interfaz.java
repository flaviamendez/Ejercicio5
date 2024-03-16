package org.pr2.interfaces;

import org.pr2.dominio.Libreta;

/**
 * Implementa una interfaz de línea de comandos para la libreta de contactos.
 */
public class Interfaz {

    /**
     * Inicia la interfaz con parámetros.
     * @param args puede ser <i>add nombre contacto</i> (p.ej. <i>add Juan 653421367</i>) para añadir contacto,
     *             o <i>rm nombre</i> para eliminar un contacto, o <i>show</i> para mostrar todos los contactos de la libreta.
     */
    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("Uso: java Interfaz [add|rm|show|hoja]");
            return;
        }

        Libreta libreta = new Libreta();
        String comando = args[0];

        switch (comando) {
            case "add":
                if (args.length < 3) {
                    System.out.println("Uso: java Interfaz add nombre telefono");
                    return;
                }
                libreta.annadirContacto(args[1], args[2]);
                System.out.println("Contacto agregado: " + args[1] + " " + args[2]);
                break;
            case "rm":
                if (args.length < 2) {
                    System.out.println("Uso: java Interfaz rm nombre");
                    return;
                }
                libreta.borrarContacto(args[1]);
                System.out.println("Contacto eliminado: " + args[1]);
                break;
            case "show":
                System.out.println("Contactos en la libreta:");
                System.out.println(libreta);
                break;
            case "hoja":
                libreta.generarHojaDeCalculo();
                System.out.println("Hoja de cálculo generada en output/contactos.ods");
                break;
            default:
                System.out.println("Comando no reconocido: " + comando);
                break;
        }
    }
}
