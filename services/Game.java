package com.company.main.services;

import com.company.main.models.Player;
import com.company.main.shared.GameLogic;
import com.company.main.ui.GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Game {
    public int playerId = 1; //temp change later
    public int enemyId;
    public GUI ui;
    public Player playerInstance;
    String[] champs = {"Wizard", "Knight", "Fairy", "Warden", "Dragon", "Upgrade"};

    public Game(){
        ui = new GUI(756,480);
        ui.setUpUi();
        buttonHandler();
        handlePlayer();

        ui.HEALTH = new JLabel("Your HP: \n" + playerInstance.health );
        ui.GOLD = new JLabel("Your Gold: \n" + playerInstance.gold );

        if(playerInstance.playerNumber == 1){
            ui.systemInfo.setText("->You are player " + playerInstance.playerNumber + "\n->Waiting for second player");
        } else {
            ui.systemInfo.setText("->You are player " + playerInstance.playerNumber + "\nBoth players connected");
        }
        ui.statusBar.add(ui.HEALTH);
        ui.statusBar.add(ui.GOLD);

        ui.toggleButtons();

    }

    public void handlePlayer(){ // handling player interactions
        playerInstance = new Player(100,6);
        if( playerInstance.playerNumber == 1){
            ui.isMyTurn = true;
        } else if( playerId  == 0){
            System.out.println("Error connecting to server!");
        }
        else {
            System.out.println("not my turn");
            ui.isMyTurn = false;
        }
    }

    public void buttonHandler(){
        ActionListener al = e -> {
            JButton buttonPressed = (JButton) e.getSource();
            String buttonText = buttonPressed.getText();
            System.out.println(buttonText);

            int whichObject = GameLogic.getPressedButton(buttonText); // id of pressed button
            ui.gameLogs.setText("you bought " + champs[whichObject]);
            playerInstance.gold -= GameLogic.getHeroPrice(buttonText);
            ui.GOLD.setText("Your gold: " + playerInstance.gold);
            playerInstance.myHeroes[whichObject][1] = playerInstance.myHeroes[whichObject][1]++;
            GameLogic.showHeroes(playerInstance, ui);
            checkIfEnoughGold();
        };
        ui.button1.addActionListener(al);
        ui.button2.addActionListener(al);
        ui.button3.addActionListener(al);
        ui.button4.addActionListener(al);
        ui.button5.addActionListener(al);
        ui.button6.addActionListener(al);
    }

    public void checkIfEnoughGold(){
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
        if(playerInstance.gold < 6){
            ui.button5.setEnabled(false);
        }
        if(playerInstance.gold < 4){
            ui.button6.setEnabled(false);
        }

    }

}