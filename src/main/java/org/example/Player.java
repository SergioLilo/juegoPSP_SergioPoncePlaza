package org.example;

import java.io.*;
import java.net.Socket;

public class Player extends Thread {
    String nombre;
    int pv = 50;
    int dinero = 25;
    private Socket socket;


    public Player(String nombre, Socket socket) {
        this.nombre = nombre;
        this.socket = socket;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    @Override
    public void run() {

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream());
            String receivedMessage;
            boolean salir=true;

                while ((receivedMessage = input.readLine()) != null && salir) {
                    String[] command = receivedMessage.split(" ");
                    if (command[0].equals("/atacar")) {

                        String jugadorAtacado = command[1];
                        atacar(jugadorAtacado);

                    }
                    if (command[0].equals("/resumen")) {

                        resumen();
                    }

                    if (receivedMessage.equalsIgnoreCase("/salir")) {
                        salir = false;
                    }
                    System.out.println(nombre + ": " + receivedMessage);
                }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nombre + " ha salido del juego.");
    }
    public void atacar(String jugadorAtacado) throws InterruptedException {
        synchronized (ServerStart.players){
            if (dinero>=5){
        for (Player p: ServerStart.players){
            if (p.getNombre().equals(jugadorAtacado)){
                if (p.getPv()>0 && p.getPv()<=100 && dinero>=5){
                p.setPv(p.getPv()-10);
                setDinero(getDinero()-5);
                    System.out.println(p.getPv());
                    Thread.sleep(100);
                    System.out.println("[SERVIDOR]"+ this.nombre+" atacó a "+jugadorAtacado+" (-10 PV) " );

                }else {
                    System.out.println("[SERVIDOR]"+ this.nombre+" atacó a "+jugadorAtacado+ " pero no surtió efecto.");
                }
            }
        }
            }


        }

    }
    public void resumen() {

        synchronized (ServerStart.players){
        for (Player p: ServerStart.players){
            System.out.println(p.toString());
        }
        }

    }

    @Override
    public String toString() {
        return  nombre + " | pv=" + pv + " dinero=" + dinero+" |";
    }
}
