package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloGestionMensajes extends Thread {
    AppServidor appServidor;
    DataInputStream flujoEntrada;
    Socket socketComunicacion;

    public HiloGestionMensajes(DataInputStream flujoEntrada, AppServidor appServidor, Socket socketComunicacion) {
        this.appServidor = appServidor;
        this.flujoEntrada = flujoEntrada;
        this.socketComunicacion = socketComunicacion;
    }

    @Override
    public void run() {
        try {
            String mensaje;
            while ((mensaje = flujoEntrada.readUTF()) != null) {
                appServidor.areaMensajes.append("Mensaje recibido: " + mensaje + "\n");
                appServidor.reenviarMensaje(mensaje, socketComunicacion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
