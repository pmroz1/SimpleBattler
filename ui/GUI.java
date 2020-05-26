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

    private Container heroBuy1;
    private Container heroBuy2;
    private Container heroBuy3;
    private Container heroBuy4;
    private Container heroBuy5;
    private Container heroBuy6;

    protected JTextArea textBoard; // board for showing text
    protected JTextArea textBoard2;

    protected JTextArea info1; // gold price
    protected JTextArea info2;
    protected JTextArea info3;
    protected JTextArea info4;
    protected JTextArea info5;
    protected JTextArea info6;

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
        heroBuy1 = new JPanel();
        heroBuy2 = new JPanel();
        heroBuy3 = new JPanel();
        heroBuy4 = new JPanel();
        heroBuy5 = new JPanel();
        heroBuy6 = new JPanel();

        textBoard = new javax.swing.JTextArea();
        textBoard2 = new javax.swing.JTextArea();

        info1 = new javax.swing.JTextArea();
        info2 = new javax.swing.JTextArea();
        info3 = new javax.swing.JTextArea();
        info4 = new javax.swing.JTextArea();
        info5 = new javax.swing.JTextArea();
        info6 = new javax.swing.JTextArea();

        button1 = new JButton("3 gold"); // creating buttons
        button2 = new JButton("2 gold");
        button3 = new JButton("1 gold");
        button4 = new JButton("2 gold");
        button5 = new JButton("6 gold");
        button6 = new JButton("4 gold");

        info1.setText("Wizard");
        info2.setText("Knight");
        info3.setText("Fairy");
        info4.setText("Warden");
        info5.setText("Dragon");
        info6.setText("Upgrade");
    }

    public void setUpUi(){
        this.setSize(width, height); // basic configuration
        this.setTitle("Player #" + playerId + "Simple Autobattler");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        container.setLayout(new GridLayout(2,0)); // adding grid layout to our container
        upper.setLayout(new GridLayout(2,1));
        lower.setLayout(new GridLayout(1,6));

        heroBuy1.setLayout(new GridLayout(2,1));
        heroBuy2.setLayout(new GridLayout(2,1));
        heroBuy3.setLayout(new GridLayout(2,1));
        heroBuy4.setLayout(new GridLayout(2,1));
        heroBuy5.setLayout(new GridLayout(2,1));
        heroBuy6.setLayout(new GridLayout(2,1));

        statusBar.setLayout(new GridLayout(2,1));

        container.add(upper);
        container.add(lower);

        textBoard.setText("Wizard : 3 gold\n Knight : 2 gold\nFairy : 1 gold\nWarden : 2 gold\nDragon : 6 gold\nLvl Up : 4 gold");
        textBoard.setWrapStyleWord(true);
        textBoard.setLineWrap(true);
        textBoard.setEditable(false);

        textBoard.setWrapStyleWord(true);
        textBoard.setLineWrap(true);
        textBoard.setEditable(false);

        statusBar.add(new JLabel("Your Gold: \n69"));
        statusBar.add(new JLabel("Your HP: \n69"));

        upper.add(statusBar); upper.add(gameScreen);
        heroBuy1.add(info1); heroBuy2.add(info2); heroBuy3.add(info3); heroBuy4.add(info4); heroBuy5.add(info5); heroBuy6.add(info6);
        heroBuy1.add(button1); heroBuy2.add(button2); heroBuy3.add(button3); heroBuy4.add(button4); heroBuy5.add(button5); heroBuy6.add(button6);

        lower.add(textBoard);
        lower.add(textBoard2);
        lower.add(heroBuy1); lower.add(heroBuy2); lower.add(heroBuy3); lower.add(heroBuy4); lower.add(heroBuy5); lower.add(heroBuy6);

        this.setVisible(true); // we can now see our app
    }
}