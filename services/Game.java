package com.company.main.services;

import com.company.main.models.Player;
import com.company.main.shared.GameLogic;
import com.company.main.ui.GUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game {
    public int playerId = 1; //temp change later
    public int enemyId;
    public GUI ui;
    public Player playerInstance;


    String[] champs = {"Wizard", "Knight", "Fairy", "Warden", "Dragon", "NEXT TURN"};

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
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                handlePlayer();
//            }
//        });
//        t.start();
    }

    public void handlePlayer(){ // handling player interactions
        playerInstance = new Player(100,6);
        if( playerInstance.playerNumber == 1){
            ui.isMyTurn = true;
        } else if( playerId  == 0){
            System.out.println("Error connecting to server!");
        }
        else {
            ui.isMyTurn = false;
            System.out.println("not my turn");
        }
        ui.toggleButtons();
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

            playerInstance.listOfHeroes.add(whichObject);
            GameLogic.showHeroes(playerInstance, ui);
            GameLogic.checkIfEnoughGold(playerInstance, ui);
            if(whichObject == 5){
                ui.isMyTurn = false;
                ui.toggleButtons();
            }

            playerInstance.cl.csc.sendButtonPressed(whichObject);

            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    handleTurn();
                }
            });
            th.start();
        };
        ui.button1.addActionListener(al);
        ui.button2.addActionListener(al);
        ui.button3.addActionListener(al);
        ui.button4.addActionListener(al);
        ui.button5.addActionListener(al);
        ui.button6.addActionListener(al);
    }

    public void handleTurn() {
        int n = playerInstance.cl.csc.receiveButtonPressed();
        ui.gameLogs.setText("Enemy clicked button #" + n);
    }

//        enemyPoints += values[n-1];
//        System.out.println("enemy has #" + enemyPoints + " points");
//        if(playerID == 1 && maxTurns == turnsMade){
//            checkWinner();
//        } else {
//            buttonsEnabled = true;
//        }

}