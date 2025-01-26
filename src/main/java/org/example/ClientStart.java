package org.example;

import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class ClientStart  {

    public static void main(String[] args) {
      enviarMensaje();
    }


    public static  void enviarMensaje() {
        String host = "localhost";
        int portReceptor = 5555;

        try (Socket socket = new Socket(host, portReceptor);
             Scanner teclado = new Scanner(System.in)) {

            System.out.println("EMISOR: *** Conectado al servidor ***");
            System.out.println("Escribe un mensaje (o '/exit' para salir):");
            String mensaje="";
            while (!mensaje.equalsIgnoreCase("/salir")){
                mensaje = teclado.nextLine();
                OutputStream output = socket.getOutputStream();

                output.write((mensaje + "\n").getBytes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*  String host = "localhost";
        int portReceptor = 5555;

        try (Socket socket = new Socket(host, portReceptor);
             Scanner teclado = new Scanner(System.in)) {

            System.out.println("EMISOR: *** Conectado al servidor ***");
            System.out.println("Escribe un mensaje (o '/exit' para salir):");
            String mensaje = "";
            while (!mensaje.equalsIgnoreCase("/exit")) {
                mensaje = teclado.nextLine();

                OutputStream output = socket.getOutputStream();
                output.write((mensaje + "\n").getBytes());


            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    * */
}

