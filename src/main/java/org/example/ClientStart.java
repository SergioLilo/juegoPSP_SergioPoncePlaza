package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class ClientStart  {

    public static void main(String[] args) {

        String host = "localhost";
        int portReceptor = 5555;
        enviarMensaje(host,portReceptor);
    }


    public static  void enviarMensaje(String host,int portReceptor) {


        try (Socket socket = new Socket(host, portReceptor);
             Scanner teclado = new Scanner(System.in)) {

          //  BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("EMISOR: *** Conectado al servidor ***");
            System.out.println("Escribe un mensaje (o '/salir' para salir):");
            String mensaje="";
            while (!mensaje.equalsIgnoreCase("/salir")){
               mensaje = teclado.nextLine();
                OutputStream output = socket.getOutputStream();

               output.write((mensaje + "\n").getBytes());
                //String response = in.readLine();
                //System.out.println("Servidor dice: " + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

 public  static void imprimir(String mensaje)   {
     String host = "localhost";
     int portReceptor = 5555;
     try (Socket socket = new Socket(host, portReceptor)){

         OutputStream output = socket.getOutputStream();

         output.write((mensaje + "\n").getBytes());
        } catch (UnknownHostException e) {
         throw new RuntimeException(e);
     } catch (IOException e) {
         throw new RuntimeException(e);
     }

 }
}


