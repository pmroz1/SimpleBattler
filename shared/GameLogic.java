package com.company.main.shared;

import com.company.main.models.Hero;

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
}
//        button1 = new JButton("3 gold"); // creating buttons
//        button2 = new JButton("2 gold");
//        button3 = new JButton("1 gold");
//        button4 = new JButton("5 gold");
//        button5 = new JButton("6 gold");
//        button6 = new JButton("4 gold");