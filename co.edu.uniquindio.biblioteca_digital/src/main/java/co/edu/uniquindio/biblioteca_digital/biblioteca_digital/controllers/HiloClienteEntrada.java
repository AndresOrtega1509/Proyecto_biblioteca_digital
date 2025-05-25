package co.edu.uniquindio.biblioteca_digital.biblioteca_digital.controllers;

import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.IOException;

public class HiloClienteEntrada extends Thread {
    private DataInputStream flujoEntrada;
    private TextArea areaMensajes;

    public HiloClienteEntrada(DataInputStream flujoEntrada, TextArea areaMensajes) {
        this.flujoEntrada = flujoEntrada;
        this.areaMensajes = areaMensajes;
    }

    @Override
    public void run() {
        try {
            String mensaje;
            while ((mensaje = flujoEntrada.readUTF()) != null) {
                areaMensajes.appendText(mensaje + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

