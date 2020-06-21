package com.company.main.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


/**
 * <h1>GameServer</h1>
 * Klasa - Klient odpowiedzialną za część klienta działania gry.
 * Klasę tę jest wywoływana z klasy gracza( Player)
 * @author  Piotr Mróz
 * @since   2020-06-17
 * */
public class Client {
    public int player; // id of player
    public int enemy; // enemy player id
    public ClientSideCon csc;

    /**
     * Metoda tworzy nowe połączenie z serwerem
     */
    public void connectToServer(){
        csc = new ClientSideCon();
    }

    /**
     * <h1>ClientSideCon</h1>
     * Klasa wewnętrzna, odpowiedzialna za zarządzanie połączeniami ze strony klienta
     * @author  Piotr Mróz
     * @since   2020-06-17
     * */
    public class ClientSideCon {
        private Socket socket;
        private DataOutputStream dataOut;
        private DataInputStream dataIn;

        /**
         * konstruktor, tworzy nowe połączenie z serwerem
         */
        public ClientSideCon() {
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
                //System.out.println("Connected to server #player : " + player + "\n\n");
            } catch (IOException e) {
                System.out.println("Exception thrown from CSC" + e);
            }
        }

        /**
         * Metoda wysyła wciśnięty przycisk(Jbutton)
         * @param data Int : wciśnięty przycisk.
         * @exception IOException On input error.
         * @see IOException
         */
        public void sendButtonPressed(int data){
            try{
                dataOut.writeInt(data);
                dataOut.flush();
            }catch(IOException e){
                System.out.println("Exception formsenbuttonclient : " + e);
            }
        }

        /**
         * Metoda odbiera wciśnięty przycisk(Jbutton)
         * @exception IOException On input error.
         * @see IOException
         */
        public int receiveButtonPressed() {
            int n = -1;
            try {
                n = dataIn.readInt();
            } catch (IOException e) {
                System.out.println("Exception form reciveBytton client : " + e);
            }
            return n;
        }
    }
}
