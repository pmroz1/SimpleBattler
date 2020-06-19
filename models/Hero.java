package com.company.main.models;

public class Hero {
    public String name;
    public int Attack;
    public int Defense;
    public int Hp; //HealthPack

    public Hero(String name, int a, int d){
        this.name = name;
        this.Attack = a;
        this.Defense = d;
    }
}