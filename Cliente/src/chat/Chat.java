package chat;

import vista.VistaCliente;
import control.Conector;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author E304
 */
public class Chat {

    public static Conector servidor, cliente;

    public Chat() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        VistaCliente vistaCliente = new VistaCliente();
        vistaCliente.setVisible(true);
    }

    public static void initClient(String ip) {
        cliente = new Conector(ip);
        cliente.start();
        JOptionPane.showMessageDialog(null, "Conectado.");
        System.out.println("Conectado.");
    }

}
