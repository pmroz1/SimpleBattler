package com.company.main.models;

import com.company.main.services.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    public int health;
    public int gold;
    public int playerNumber;
    //public int [] heroCounter = {0,0,0,0,0,0};
    public int[][] myHeroes = new int[6][1];
    public List<Integer> myField;

    // set ups values
    public Player(int h, int g){
        this.health = h;
        this.gold = g;
        Client cl = new Client();
        cl.connectToServer();
        playerNumber = cl.player;
        System.out.println("Im player no: " + playerNumber);
        for (int i = 0; i< myHeroes.length; ++i){
            myHeroes[i][0] = 0;
        }
    }

}