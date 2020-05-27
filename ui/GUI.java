package com.company.main.ui;

import com.company.main.models.Player;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private int width; // width, height of client app interface
    private int height;

    // Handle player
    public Player p1;

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

    protected JTextPane textBoard; // board for showing text
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

        textBoard = new javax.swing.JTextPane();
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
        button4 = new JButton("2 gold");
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

        textBoard.setText("Your stats:\n\n "+ "HP: 100" + "\nGold: 5");
        textBoard.setBackground( Color.CYAN);
        textBoard.setEditable(false);

        StyledDocument doc = textBoard.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        allInfoPanels.add(info1);
        allInfoPanels.add(info2);
        allInfoPanels.add(info3);
        allInfoPanels.add(info4);
        allInfoPanels.add(info5);
        allInfoPanels.add(info6);

        statusBar.add(new JLabel("Your Gold: \n69"));
        statusBar.add(new JLabel("Your HP: \n69"));

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

        lower.add(textBoard);
        lower.add(systemInfo);
        lower.add(heroBuy1); lower.add(heroBuy2); lower.add(heroBuy3); lower.add(heroBuy4); lower.add(heroBuy5); lower.add(heroBuy6);

        systemInfo.setEditable(false);
        systemInfo.setBackground(Color.pink);
        systemInfo.setText("->You are player 1\n->Waiting for player 2");
        this.setVisible(true); // we can now see our app
    }

    public void handlePlayer(){
        p1 = new Player(100,6);
    }
}