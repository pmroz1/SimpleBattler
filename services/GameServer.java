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

    //game variables
    private int connectedPlayers;
    public GameServer(){
        System.out.println("---running server---");
        connectedPlayers = 0;

        try{
            serverSocket = new ServerSocket(69686); // we will use port 69686 for game
        }catch(IOException e){
            System.out.println("From Server constructor :\n" + e);
        }
    }
    // connections handling
    public void acceptConnections(){
        // pass
    }

    private class ServerSideCon() implements Runnable{
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;

        public ServerSideCon(Socket socketIn){
            socket = socketIn;
            try{
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
            } catch(IOException e) {
                System.out.println("Exception in SSC constructor : " + e);
            }
        }

        public void run(){
            try{
                //pass
                System.out.println("xd");
            } catch(IOException e){
                System.out.println("Exception in SSC run(f) : " + e);
            }
        }
    }

}