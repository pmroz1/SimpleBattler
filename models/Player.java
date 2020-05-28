package com.company.main.models;

import com.company.main.services.Client;

import java.util.HashMap;
import java.util.Map;

public class Player {
    public int health;
    public int gold;
    public int playerNumber;
    public Map<String, Integer> numberMapping = new HashMap<>();

    // set ups values
    public Player(int h, int g){
        this.health = h;
        this.gold = g;
        Client cl = new Client();
        cl.connectToServer();
        playerNumber = cl.player;
        System.out.println("Im player no: " + playerNumber);
    }

}