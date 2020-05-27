package com.company.main.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public int player; // id of player
    public int enemy; // enemy player id
    public ClientSideCon csc;

    public void connectToServer(){
        csc = new ClientSideCon();
        System.out.println("-----xd-----");
    }

    //inner class for handling client connection
    public class ClientSideCon {
        private Socket socket;
        private DataOutputStream dataOut;
        private DataInputStream dataIn;

        public ClientSideCon() {
            System.out.println("-----Client Running-----");
            try {
                //get data streams
                socket = new Socket("localhost", 6968);
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());

                player = dataIn.readInt();
                System.out.println("Connected to server #player : " + player);
            } catch (IOException e) {
                System.out.println("Exception thrown from CSC" + e);
            }
        }
        //to do close connection
    }
}
