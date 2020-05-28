package com.company.main.ui;

import com.company.main.models.Player;
import com.company.main.shared.GameLogic;
import com.company.main.shared.Heroes;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    private int width; // width, height of client app interface
    private int height;
    private boolean isMyTurn = true;
    String[] champs = {"Wizard", "Knight", "Fairy", "Warden", "Dragon", "Upgrade"};
    //private String gameLogs;

    // Handle player
    public Player playerInstance;

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

    protected JTextPane gameLogs; // board for showing text
    protected JTextPane textBoard2;
    protected JTextPane systemInfo; // system debug

    protected JTextPane info1; // gold price
    protected JTextPane info2;
    protected JTextPane info3;
    protected JTextPane info4;
    protected JTextPane info5;
    protected JTextPane info6;

    protected JButton button1;//buttons
    protected JButton button2;
    protected JButton button3;
    protected JButton button4;
    protected JButton button5;
    protected JButton button6;

    protected int playerId = 1; //temp change later
    protected int enemyId;

    private ArrayList<JTextPane> allInfoPanels = new ArrayList<>();

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

        gameLogs = new javax.swing.JTextPane();
        textBoard2 = new javax.swing.JTextPane();
        systemInfo = new javax.swing.JTextPane();

        info1 = new javax.swing.JTextPane();
        info2 = new javax.swing.JTextPane();
        info3 = new javax.swing.JTextPane();
        info4 = new javax.swing.JTextPane();
        info5 = new javax.swing.JTextPane();
        info6 = new javax.swing.JTextPane();

        button1 = new JButton("3 gold"); // creating buttons
        button2 = new JButton("2 gold");
        button3 = new JButton("1 gold");
        button4 = new JButton("5 gold");
        button5 = new JButton("6 gold");
        button6 = new JButton("4 gold");

        info1.setText("\n\nWizard");
        info2.setText("\n\nKnight");
        info3.setText("\n\nFairy");
        info4.setText("\n\nWarden");
        info5.setText("\n\nDragon");
        info6.setText("\n\nUpgrade");

        handlePlayer();
    }

    public void setUpUi(){
        this.setSize(width, height); // basic configuration
        this.setTitle("Simple Autobattler");
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

        gameLogs.setText("Game logs");
        gameLogs.setBackground( Color.CYAN);
        gameLogs.setEditable(false);

        StyledDocument doc = gameLogs.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        allInfoPanels.add(info1);
        allInfoPanels.add(info2);
        allInfoPanels.add(info3);
        allInfoPanels.add(info4);
        allInfoPanels.add(info5);
        allInfoPanels.add(info6);

        statusBar.add(new JLabel("Your HP: \n" + playerInstance.health ));
        statusBar.add(new JLabel("Your Gold: \n" + playerInstance.gold));

        for( JTextPane x : allInfoPanels){
            x.setEditable(false);
            x.setBackground(Color.gray);
            StyledDocument doc2 = x.getStyledDocument();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc2.setParagraphAttributes(0, doc2.getLength(), center, false);
        }

        upper.add(statusBar); upper.add(gameScreen);
        heroBuy1.add(info1); heroBuy2.add(info2); heroBuy3.add(info3); heroBuy4.add(info4); heroBuy5.add(info5); heroBuy6.add(info6);
        heroBuy1.add(button1); heroBuy2.add(button2); heroBuy3.add(button3); heroBuy4.add(button4); heroBuy5.add(button5); heroBuy6.add(button6);

        lower.add(gameLogs);
        lower.add(systemInfo);
        lower.add(heroBuy1); lower.add(heroBuy2); lower.add(heroBuy3); lower.add(heroBuy4); lower.add(heroBuy5); lower.add(heroBuy6);

        systemInfo.setEditable(false);
        systemInfo.setBackground(Color.pink);
        if(playerInstance.playerNumber == 1){
            systemInfo.setText("->You are player " + playerInstance.playerNumber + "\n->Waiting for second player");
        } else {
            systemInfo.setText("->You are player " + playerInstance.playerNumber + "\nBoth players connected");
        }
        toggleButtons();
        this.setVisible(true); // we can now see our app
    }

    public void toggleButtons(){ // makes buttons clickable or not :)
        button1.setEnabled(isMyTurn);
        button2.setEnabled(isMyTurn);
        button3.setEnabled(isMyTurn);
        button4.setEnabled(isMyTurn);
        button5.setEnabled(isMyTurn);
        button6.setEnabled(isMyTurn);
    }

    public void handlePlayer(){ // handling player interactions
        playerInstance = new Player(100,6);
        if( playerInstance.playerNumber == 1){
            isMyTurn = true;
        } else if( playerId  == 0){
            System.out.println("Error connecting to server!");
        }
        else {
            isMyTurn = false;
        }
    }

    public void buttonHandler(){
        ActionListener al = e -> {
            JButton buttonPressed = (JButton) e.getSource();
            String buttonText = buttonPressed.getText();
            System.out.println(buttonText);
            int whichObject = GameLogic.getPressedButton(buttonText); // id of pressed button
            //gameLogs.setText("U Bought" + champs[]);

            System.out.println("Knight" + Heroes.Knight.ordinal());
        };

        button1.addActionListener(al);
        button2.addActionListener(al);
        button3.addActionListener(al);
        button4.addActionListener(al);
        button5.addActionListener(al);
        button6.addActionListener(al);
    }
}