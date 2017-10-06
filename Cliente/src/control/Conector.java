package control;

import vista.VistaCliente;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
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
public class Conector extends Thread {

    private Socket socket;
    private ServerSocket servidorSocket;
    private InputStreamReader entradaSocket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto = 8180;

    public Conector() {

    }

    public Conector(String ip) {
        this.conectar(ip);
    }

    private void conectar(String ip) {
        try {
            this.socket = new Socket(ip, this.puerto);

            this.entradaSocket = new InputStreamReader(socket.getInputStream());
            this.entrada = new BufferedReader(entradaSocket);

            this.salida = new DataOutputStream(socket.getOutputStream());
            this.salida.writeUTF("Conectado- \n");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        String texto;
        try {
            while (true) {
                texto = this.entrada.readLine();
                VistaCliente.jTextArea1.setText(VistaCliente.jTextArea1.getText() + "\n" + texto);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void enviarMensaje(String mensaje) {
        System.out.println("Enviando...");
        try {
            this.salida.writeUTF(mensaje + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Problema al enviar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Problema al enviar: " + ex.getMessage());
        }
    }

    public String leerMensaje() {
        try {
            return this.entrada.readLine();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        } finally {
            return null;
        }
    }

    public void desconectar() {
        try {
            this.socket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        }
        try {
            this.servidorSocket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
