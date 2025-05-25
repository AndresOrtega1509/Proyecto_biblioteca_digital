package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloGestionClientes extends Thread {

    AppServidor appServidor;

    public HiloGestionClientes(AppServidor appServidor) {
        this.appServidor = appServidor;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(appServidor.puerto)) {
            appServidor.areaMensajes.append("Servidor iniciado, esperando lectores...\n");

            while (true) {
                Socket socketComunicacion = serverSocket.accept();
                appServidor.areaMensajes.append("Lector conectado.\n");

                synchronized (appServidor.clientesConectados) {
                    appServidor.clientesConectados.add(socketComunicacion);
                }

                DataInputStream flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());
                HiloGestionMensajes hiloGestionMensajes = new HiloGestionMensajes(flujoEntrada,appServidor,socketComunicacion);
                hiloGestionMensajes.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}