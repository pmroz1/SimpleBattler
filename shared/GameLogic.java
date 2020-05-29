package com.company.main.shared;

import com.company.main.models.Player;
import com.company.main.ui.GUI;

import javax.swing.*;

public class GameLogic {
    public static int getPressedButton(String str){
        return switch (str) {
            case "3 gold" -> Heroes.Wizard.ordinal();
            case "2 gold" -> Heroes.Knight.ordinal();
            case "1 gold" -> Heroes.Fairy.ordinal();
            case "5 gold" -> Heroes.Warden.ordinal();
            case "6 gold" -> Heroes.Dragon.ordinal();
            case "4 gold" -> Heroes.Upgrade.ordinal();
            default -> 0;
        };
    }
    public static int getHeroPrice(String str){
        return switch (str) {
            case "3 gold" -> 3;
            case "2 gold" -> 2;
            case "1 gold" -> 1;
            case "5 gold" -> 5;
            case "6 gold" -> 6;
            case "4 gold" -> 4;
            default -> 0;
        };
    }

    public static void showHeroes(Player p, GUI g){
        for(int i = 0; i< p.myHeroes.length; ++i){
            if(p.myHeroes[i][1] != 0){
                p.myField.add(i);
                System.out.println("My heroes: " + i);
            }
        }
        g.heroesOnfield.add(new JLabel("\"Heroes on field: \" XD"));
    }
}
//        button1 = new JButton("3 gold"); // creating buttons
//        button2 = new JButton("2 gold");
//        button3 = new JButton("1 gold");
//        button4 = new JButton("5 gold");
//        button5 = new JButton("6 gold");
//        button6 = new JButton("4 gold");