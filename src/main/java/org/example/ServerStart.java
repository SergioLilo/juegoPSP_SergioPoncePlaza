package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerStart {
    static List<Player> players=new ArrayList<>();
    public static void main(String[] args) {


        String receivedMessage="";
        int port = 5555;

        Map<String, String> differentPlayers = new HashMap<>();

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            int numPlayers = 1;
            System.out.println("Starting...");

            while (true){
                Socket socket = serverSocket.accept();
                String emisorClave = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();


                if (!differentPlayers.containsKey(emisorClave)) {
                    differentPlayers.put(emisorClave, "Jugador" + numPlayers);
                    numPlayers++;
                    Player jugadorNuevo =new Player(differentPlayers.get(emisorClave),socket);
                    System.out.println("[SERVIDOR]: "+jugadorNuevo.getNombre()+" Ha entrado");
                    jugadorNuevo.start();
                    players.add(jugadorNuevo);
                }


                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                receivedMessage = input.readLine();

                    System.out.println(  differentPlayers.get(emisorClave)+": " + receivedMessage);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Player recuperarJugador(Map<String, String> jugadoresDiferentes, List<Player> jugadores, String emisorClave){

        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getNombre().equals(jugadoresDiferentes.get(emisorClave))){
                return jugadores.get(i);
            }
        }
        return null;
    }

}
