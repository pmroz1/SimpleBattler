package com.company.main.models;

import com.company.main.services.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>Player</h1>
 * Klasa zawiera wszystkie informacje niezbętne do obsługi gracza
 * @author  Piotr Mróz
 * @since   2020-06-17
 * */
public class Player {
    public int health;
    public int gold;
    public int playerNumber;
    public int enemyHealth;
    public int defense;
    public int enemyDefense;
    public Client cl;

    public ArrayList<Integer> listOfHeroes = new ArrayList<>();
    public ArrayList<Integer> heroesOnField = new ArrayList<>();

    public ArrayList<Hero> enemyHeroesOnField = new ArrayList();
    public ArrayList<Hero> listOfEnemyHeroes = new ArrayList();

    public ArrayList<Hero> Heroes = new ArrayList<>();


    /**
     * konstruktor, inicjuje takie zmienne jak hp, gold, defense
     * @param hp INT : zdrowie gracza
     * @param g INt : złoto gracza
     */
    public Player(int hp, int g){
        this.health = hp;
        this.gold = g;
        this.enemyHealth = 100;
        this.defense = 0;
        this.enemyDefense = 0;

        initHeroes();
        cl = new Client();
        cl.connectToServer();
        playerNumber = cl.player;
        //System.out.println("Im player no: " + playerNumber);
    }

    /**
     * metod inicjalizuję listę(Hero) postaci
     */
    public void initHeroes(){
        int i;
        for (i = 0 ; i< 5; ++i){
            switch (i) {
                case 0 -> {
                    Hero wizard = new Hero("Wizard", 3, 1);
                    Heroes.add(wizard);
                }
                case 1 -> {
                    Hero Knight = new Hero("Knight", 2, 2);
                    Heroes.add(Knight);
                }
                case 2 -> {
                    Hero Fairy = new Hero("Fairy", 1, 1);
                    Heroes.add(Fairy);
                }
                case 3 -> {
                    Hero Warden = new Hero("Warden", 1, 5);
                    Heroes.add(Warden);
                }
                case 4 -> {
                    Hero Dragon = new Hero("Dragon", 4, 5);
                    Heroes.add(Dragon);
                }
            }
        }
    }
}