package com.company.main.ui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private int width; // width, height of client app interface
    private int height;

    private Container container;//layout
    private Container upper;
    private Container lower;
    private Container statusBar;
    private Container gameScreen;

    protected JTextArea textBoard; // board for showing text

    protected JButton button1;//buttons
    protected JButton button2;
    protected JButton button3;
    protected JButton button4;
    protected JButton button5;
    protected JButton button6;

    protected int playerId = 1; //temp change later
    protected int enemyId;

    public GUI(int w, int h){
        this.width = w;
        this.height = h;

        container = this.getContentPane();
        lower = new JPanel();
        statusBar = new JPanel();
        gameScreen = new JPanel();
        upper = new JPanel();

        textBoard = new javax.swing.JTextArea();

        button1 = new JButton("Wizard"); // creating buttons
        button2 = new JButton("Knight");
        button3 = new JButton("Fairy");
        button4 = new JButton("Warden");
        button5 = new JButton("Dragon");
        button6 = new JButton("Upgrade");
    }

    public void setUpUi(){
        this.setSize(width, height); // basic configuration
        this.setTitle("Player #" + playerId + "Simple Autobattler");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        container.setLayout(new GridLayout(2,0)); // adding grid layout to our container
        upper.setLayout(new GridLayout(2,1));
        lower.setLayout(new GridLayout(1,6));

        statusBar.setLayout(new GridLayout(2,1));

        container.add(upper);
        container.add(lower);

        textBoard.setText("Wizard : 3 gold\n Knight : 2 gold\nFairy : 1 gold\nWarden : 2 gold\nDragon : 6 gold\nLvl Up : 4 gold");
        textBoard.setWrapStyleWord(true);
        textBoard.setLineWrap(true);
        textBoard.setEditable(false);

        statusBar.add(new JLabel("Your Gold: \n69"));
        statusBar.add(new JLabel("Your HP: \n69"));

        upper.add(statusBar); upper.add(gameScreen);
        lower.add(textBoard); lower.add(button1); lower.add(button2); lower.add(button3); lower.add(button4); lower.add(button5); lower.add(button6);

        this.setVisible(true); // we can now see our app
    }
}