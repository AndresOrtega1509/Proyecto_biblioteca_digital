package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AppServidor extends JFrame {
    int puerto = 8081;
    JTextArea areaMensajes;
    List<Socket> clientesConectados = new ArrayList<>();

    public AppServidor() {
        setTitle("Servidor de Lectores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Área de mensajes
        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        add(new JScrollPane(areaMensajes), BorderLayout.CENTER);

        HiloGestionClientes hilo = new HiloGestionClientes(this);
        hilo.start();
    }

    void reenviarMensaje(String mensaje, Socket remitente) {
        synchronized (clientesConectados) {
            for (Socket cliente : clientesConectados) {
                if (cliente != remitente) {
                    try {
                        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
                        flujoSalida.writeUTF(mensaje);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppServidor servidor = new AppServidor();
            servidor.setVisible(true);
        });
    }
}

