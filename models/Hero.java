package com.company.main.models;

/**
 * <h1>Hero</h1>
 * Klasa ta tworzy bohatera zawiera takie dane jak wartość jego ataku lub obrony
 * @author  Piotr Mróz
 * @since   2020-06-17
 * */
public class Hero {
    public String name;
    public int Attack;
    public int Defense;

    /**
     * Metoda oblicza wartość obrażeń gracza iterując listę jego postaci
     * @param name String : nazwa bohatera
     * @param a Int : atak bohatera
     * @param d Int : obrona bohatera
     */
    public Hero(String name, int a, int d){
        this.name = name;
        this.Attack = a;
        this.Defense = d;
    }
}