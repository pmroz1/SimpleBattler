package com.company.main.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <h1>GameServer</h1>
 * Klasa - server odpowiedzialną za serwerową część działania gry.
 * Klasę tę należy uruchomić w pierwszej kolejności
 *
 * @author  Piotr Mróz
 * @since   2020-06-17
 * */

public class GameServer {
    private ServerSocket serverSocket; //socket
    private ServerSideCon player1; // inner classes for handling player connections
    private ServerSideCon player2;
    private boolean gameOn = true;

    private int playerOneButtonClicked;

    //game variables
    private int connectedPlayers;

    /**
     * Konstruktor domyślny klasy serwer.
     * Nie przyjmuje żadnych parametrów, odpowiada za stworzenie socket'a
     */
    public GameServer(){
        //System.out.println("---running server---");
        connectedPlayers = 0;
        try{
            serverSocket = new ServerSocket(6968); // we will use port 69686 for game
        }catch(IOException e){
            System.out.println("From Server constructor :\n" + e);
        }
    }

    /**
     * Metoda odpowiedzialna za przyjmowanie połączeń
     */
    public void acceptConnections(){
        try{
            while(connectedPlayers < 3) {
                Socket soc = serverSocket.accept();
                ++connectedPlayers;
                ServerSideCon ssc = new ServerSideCon(soc, connectedPlayers);
                System.out.println("connected players : " + connectedPlayers);

                if (connectedPlayers == 1) {
                    player1 = ssc;
                } else {
                    player2 = ssc;
                }
                Thread th = new Thread(ssc); // Initialize new thread handling Server Side Connections
                th.start();
            }
        } catch(IOException e) {
            System.out.println("Exception form AcceptConnections : " + e);
        }
    }

    /**
     * <h1>ServerSideCon</h1>
     * Klasa wewnętrzna, odpowiedzialna za zarządzanie połączeniami ze strony serwera
     * @author  Piotr Mróz
     * @version 1.0
     * @since   2020-06-17
     * */
    private class ServerSideCon implements Runnable{
        private Socket socket;
        private final int playerId;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;


        /**
         * Konstruktor klasy
         * Nie przyjmuje żadnych parametrów, odpowiada za stworzenie socket'a
         * @param socketIn Socket : na którym chcemy operować
         * @param data Int : numer ID gracza
         * @exception IOException On input error.
         * @see IOException
         */
        public ServerSideCon(Socket socketIn, int data){
            socket = socketIn;
            playerId = data;
            try{
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
            } catch(IOException e) {
                System.out.println("Exception in SSC constructor : " + e);
            }
        }

        /**
         * Metoda odpowiada za zamykanie połączeń z serwerem
         * @exception IOException On input error.
         * @see IOException
         */
        public void closeConnection(){
            try{
                socket.close();
                System.out.print("-----Connection Closed-----");
            }catch (IOException e){
                System.out.println(e);
            }
        }

        /**
         * Metoda odpowiedzialna za zarządzanie przesyłem danych.
         * Nie przyjmuje żadnych parametrów.
         * @exception IOException On input error.
         * @see IOException
         */
        public void run(){
            try{
                dataOut.writeInt(playerId);

                while(gameOn){
                    if(playerId == 1){
                        playerOneButtonClicked = dataIn.readInt();
                        player2.sendButton(playerOneButtonClicked);
                    } else {
                        int playerTwoButtonClicked = dataIn.readInt();
                        player1.sendButton(playerTwoButtonClicked);
                    }
                }
                player1.closeConnection();
                player2.closeConnection();
            } catch(IOException e){
                System.out.println("Exception in SSC run(f) : " + e);
            }
        }

        /**
         * Metoda wysyła wciśnięty przycisk(Jbutton)
         * @param input Int : wciśnięty przycisk.
         * @exception IOException On input error.
         * @see IOException
         */
        public void sendButton(int input){
            try{
                dataOut.writeInt(input);
                dataOut.flush();
            }catch(IOException e){
                System.out.println("Exception in SSC send button : " + e);
            }
        }
    }

    /**
     * Metoda main rozpoczyna program
     * @param args String[] : Nie używane.
     * @exception IOException On input error.
     * @see IOException
     */
    public static void main(String[] args){
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}