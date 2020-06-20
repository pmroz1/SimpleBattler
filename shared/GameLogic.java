package com.company.main.shared;

import com.company.main.models.Hero;
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
            case "Next turn" -> Heroes.EndTurn.ordinal();
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
            case "Next turn" -> -2;
            default -> -400;
        };
    }

    public static int getHeroByButton(int pressedButton){
        return switch (pressedButton) {
            case 1 -> Heroes.Wizard.ordinal();
            case 2 -> Heroes.Knight.ordinal();
            case 3 -> Heroes.Fairy.ordinal();
            case 4 -> Heroes.Warden.ordinal();
            case 5 -> Heroes.Dragon.ordinal();
            case 6 -> Heroes.EndTurn.ordinal();
            default -> 0;
        };
    }

    public static void showHeroes(Player p, GUI g){
        for(int x : p.listOfHeroes){
            switch(x){
                case 0:
                    g.myHeroesOnField.add(new JLabel("Wizard"));
                    break;
                case 1:
                    g.myHeroesOnField.add(new JLabel("Knight"));
                    break;
                case 2:
                    g.myHeroesOnField.add(new JLabel("Fairy"));
                    break;
                case 3:
                    g.myHeroesOnField.add(new JLabel("Warden"));
                    break;
                case 4:
                    g.myHeroesOnField.add(new JLabel("Dragon"));
                    break;
                case 5:
                    //END TURN TODO
                    break;
            }

            p.heroesOnField.add(x);
        }
        for(Hero y : p.listOfEnemyHeroes){
            g.enemyHeroesOnField.add(new JLabel(y.name));
            p.enemyHeroesOnField.add(y);
        }
        p.listOfEnemyHeroes.clear();
        p.listOfHeroes.clear();
    }

    public static void checkIfEnoughGold(Player playerInstance, GUI ui){
        if(playerInstance.gold < 3){
            ui.button1.setEnabled(false);
        }
        if(playerInstance.gold < 2){
            ui.button2.setEnabled(false);
        }
        if(playerInstance.gold < 1){
            ui.button3.setEnabled(false);
        }
        if(playerInstance.gold < 5){
            ui.button4.setEnabled(false);
        }
        if(playerInstance.gold < 6) {
            ui.button5.setEnabled(false);
        }
        if(ui.isMyTurn){
            ui.button6.setEnabled(true);
        }
    }

    public static void toggleButtons(GUI ui, Player playerInstance){
        if(ui.isMyTurn){
            ui.toggleButtonsEnabled();
            checkIfEnoughGold(playerInstance, ui);
        }else{
            ui.toggleButtonsNotEnabled();
        }
    }

    public static int calculateDMG(Player playerInstance){
        int dmgThisTurn = 0;
        for(Hero x : playerInstance.enemyHeroesOnField){
            dmgThisTurn += x.Attack;
        }
        return dmgThisTurn;
    }
}
