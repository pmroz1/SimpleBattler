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
        System.out.println("-----Client-----");
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
                if(player == 1){
                    enemy = 2;
                } else {
                    enemy = 1;
                }
                System.out.println("Connected to server #player : " + player + "\n\n");
            } catch (IOException e) {
                System.out.println("Exception thrown from CSC" + e);
            }
        }

        public void sendButtonPressed(int data){
            try{
                dataOut.writeInt(data);
                dataOut.flush();
            }catch(IOException e){
                System.out.println("Exception formsenbuttonclient : " + e);
            }
        }

        public int receiveButtonPressed() {
            int n = -1;
            try {
                n = dataIn.readInt();
                System.out.println("Player #" + enemy + " clicked button #" + n);
            } catch (IOException e) {
                System.out.println("Exception form reciveBytton client : " + e);
            }
            return n;
        }
    }
}
