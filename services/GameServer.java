package com.company.main.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerError;

public class GameServer {
    private ServerSocket serverSocket; //socket
    private ServerSideCon player1; // inner classes for handling player connections
    private ServerSideCon player2;
    private boolean GAMEON = true;

    private int playerOneButtonClicked;
    private int playerTwoButtonClicked;

    //game variables
    private int connectedPlayers;
    public GameServer(){
        System.out.println("---running server---");
        connectedPlayers = 0;

        try{
            serverSocket = new ServerSocket(6968); // we will use port 69686 for game
        }catch(IOException e){
            System.out.println("From Server constructor :\n" + e);
        }
    }
    // connections handling
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
                    //System.out.println("Both players connected");
                }
                Thread th = new Thread(ssc); // Initialize new thread handling Server Side Connections
                th.start();
            }
        } catch(IOException e) {
            System.out.println("Exception form AcceptConnections : " + e);
        }
    }

    private class ServerSideCon implements Runnable{
        private Socket socket;
        private int playerId;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;

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

        public void closeConnection(){
            try{
                socket.close();
                System.out.print("-----Connection Closed-----");
            }catch (IOException e){
                System.out.println(e);
            }
        }

        public void run(){
            try{
                //dataOut.writeChars("Hello there general kenobi");
                System.out.println("sending player id to client #" + playerId);
                dataOut.writeInt(playerId);

                while(GAMEON){
                    if(playerId == 1){
                        playerOneButtonClicked = dataIn.readInt();
                        System.out.println("Player one clicked  #" + playerOneButtonClicked);
                        player2.sendButton(playerOneButtonClicked);
                    } else {
                        playerTwoButtonClicked = dataIn.readInt();
                        System.out.println("Player two clicked  #" + playerTwoButtonClicked);
                        player1.sendButton(playerTwoButtonClicked);
                    }
                }
                //dataOut.flush();
                //System.out.println("xd");
                player1.closeConnection();
                player2.closeConnection();
            } catch(IOException e){
                System.out.println("Exception in SSC run(f) : " + e);
            }
        }
        public void sendButton(int input){
            try{
                System.out.println("Sending pressed button #" + input);
                dataOut.writeInt(input);
                dataOut.flush();
            }catch(IOException e){
                System.out.println("Exception in SSC send button : " + e);
            }
        }
    }

    public static void main(String[] args){
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}