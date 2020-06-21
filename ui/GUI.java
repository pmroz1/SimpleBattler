package com.company.main.ui;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;

/**
 * <h1>GUI</h1>
 * Klasa odpowiedzialna za Graficzny Interfejs Użytkownika
 * @author  Piotr Mróz
 * @since   2020-06-17
 * */
@SuppressWarnings("deprecation")
public class GUI extends JFrame {
    public int width; // width, height of client app interface
    public int height;
    public boolean isMyTurn = true;
    public JFrame frame = new JFrame();

    public Container container;//layout
    public Container upper;
    public Container lower;
    public Container statusBar;

    public Container battleground;

    public Container myHeroesOnField;
    public Container enemyHeroesOnField;

    public Container heroBuy1;
    public Container heroBuy2;
    public Container heroBuy3;
    public Container heroBuy4;
    public Container heroBuy5;
    public Container heroBuy6;

    public JTextPane gameLogs; // board for showing text
    public JTextPane textBoard2;
    public JTextPane systemInfo; // system debug

    public JTextPane info1; // gold price
    public JTextPane info2;
    public JTextPane info3;
    public JTextPane info4;
    public JTextPane info5;

    public JButton button1;//buttons
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public JButton button5;
    public JButton button6;
    public JLabel GOLD;
    public JLabel HEALTH;
    public JLabel ENEMYHEALTH;
    public JLabel defense;

    public ArrayList<JTextPane> allInfoPanels = new ArrayList<>();

    /**
     * Konstruktor domyślny klasy GUI.
     * Nie przyjmuje żadnych parametrów, odpowiada za zainicjalizowanie GUI
     *
     */
    public GUI(int w, int h){
        this.width = w;
        this.height = h;

        container = this.getContentPane();
        lower = new JPanel();
        statusBar = new JPanel();

        battleground = new JPanel();
        myHeroesOnField = new JPanel();
        enemyHeroesOnField  = new JPanel();

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
        //info6 = new javax.swing.JTextPane();

        button1 = new JButton("3 gold"); // creating buttons
        button2 = new JButton("2 gold");
        button3 = new JButton("1 gold");
        button4 = new JButton("5 gold");
        button5 = new JButton("6 gold");
        button6 = new JButton("Next turn");

        info1.setText("\n\nWizard");
        info2.setText("\n\nKnight");
        info3.setText("\n\nFairy");
        info4.setText("\n\nWarden");
        info5.setText("\n\nDragon");
        //info6.setText("\n\nUpgrade");
    }

    /**
     * Metoda ta jest wywoływana raz, na samym początku prrogramu w celu ustawienia i upozycjonowania całości interfejsu
     */
    public void setUpUi(){
        this.setSize(width, height); // basic configuration
        this.setTitle("Simple Autobattler");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        frame.add(container);
        container.setLayout(new GridLayout(2,0)); // adding grid layout to our container
        upper.setLayout(new GridLayout(2,1));
        lower.setLayout(new GridLayout(1,6));

        battleground.setLayout(new GridLayout(2,1));

        heroBuy1.setLayout(new GridLayout(2,1));
        heroBuy2.setLayout(new GridLayout(2,1));
        heroBuy3.setLayout(new GridLayout(2,1));
        heroBuy4.setLayout(new GridLayout(2,1));
        heroBuy5.setLayout(new GridLayout(2,1));
        heroBuy6.setLayout(new GridLayout(1,1));

        statusBar.setLayout(new GridLayout(2,1));

        container.add(upper);
        container.add(lower);


        gameLogs.setText("Game logs");
        gameLogs.setBackground( Color.CYAN);
        gameLogs.setEditable(false);
        //gameLogs.set

        StyledDocument doc = gameLogs.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        allInfoPanels.add(info1);
        allInfoPanels.add(info2);
        allInfoPanels.add(info3);
        allInfoPanels.add(info4);
        allInfoPanels.add(info5);
        //allInfoPanels.add(info6);

        for( JTextPane x : allInfoPanels){
            x.setEditable(false);
            x.setBackground(Color.gray);
            StyledDocument doc2 = x.getStyledDocument();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc2.setParagraphAttributes(0, doc2.getLength(), center, false);
        }

        upper.add(statusBar); upper.add(battleground);
        battleground.add(enemyHeroesOnField);
        battleground.add(myHeroesOnField);

        statusBar.setBackground(Color.orange);
        enemyHeroesOnField.setBackground(Color.RED);
        myHeroesOnField.setBackground(Color.GREEN);

        heroBuy1.add(info1); heroBuy2.add(info2); heroBuy3.add(info3); heroBuy4.add(info4); heroBuy5.add(info5); //heroBuy6.add(info6);
        heroBuy1.add(button1); heroBuy2.add(button2); heroBuy3.add(button3); heroBuy4.add(button4); heroBuy5.add(button5); heroBuy6.add(button6);

        lower.add(gameLogs);
        lower.add(systemInfo);
        lower.add(heroBuy1); lower.add(heroBuy2); lower.add(heroBuy3); lower.add(heroBuy4); lower.add(heroBuy5); lower.add(heroBuy6);

        systemInfo.setEditable(false);
        systemInfo.setBackground(Color.pink);

        frame.setResizable(false);
        frame.setSize(width,height);
        frame.show();
    }

    /**
     * Metoda przełączą przyciski na aktywne
     */
    public void toggleButtonsEnabled(){ // makes buttons clickable or not :)
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
    }

    /**
     * Metoda przełącza przyciski na nieaktywne
     */
    public void toggleButtonsNotEnabled(){ // makes buttons clickable or not :)
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);
    }

    /**
     * próba rysowania postaci w polu GUI.Battleground
     */
    public class drawer extends JPanel{
        private int squareX = 50;
        private final int squareY = 50;
        private int squareW = 20;
        private int squareH = 20;

        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // do your superclass's painting routine first, and then paint on top of it.
            g.setColor(Color.RED);
            g.fillRect(squareX,squareY,squareW,squareH);
        }
    }

}