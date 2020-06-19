package com.company.main.models;

import com.company.main.services.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    public int health;
    public int gold;
    public int playerNumber;
    public Client cl;
    //public int [] heroCounter = {0,0,0,0,0,0};
    public ArrayList<Integer> listOfHeroes = new ArrayList<>();
    public ArrayList<Integer> heroesOnField = new ArrayList<>();
    public ArrayList<Hero> enemyHeroesOnField = new ArrayList();

    public ArrayList<Hero> Heroes = new ArrayList<>();


    // set ups values
    public Player(int hp, int g){
        this.health = hp;
        this.gold = g;
        initHeroes();
        cl = new Client();
        cl.connectToServer();
        playerNumber = cl.player;
        System.out.println("Im player no: " + playerNumber);
    }

    public void initHeroes(){
        int i;
        for (i = 0 ; i< 6; ++i){
            switch (i) {
                case 1 -> {
                    Hero wizard = new Hero("Wizard", 3, 1);
                    Heroes.add(wizard);
                }
                case 2 -> {
                    Hero Knight = new Hero("Knight", 2, 2);
                    Heroes.add(Knight);
                }
                case 3 -> {
                    Hero Fairy = new Hero("Fairy", 1, 1);
                    Heroes.add(Fairy);
                }
                case 4 -> {
                    Hero Warden = new Hero("Warden", 1, 5);
                    Heroes.add(Warden);
                }
                case 5 -> {
                    Hero Dragon = new Hero("Dragon", 4, 5);
                    Heroes.add(Dragon);
                }
            }
        }
        System.out.println(Heroes);
    }

}