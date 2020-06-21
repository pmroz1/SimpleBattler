package com.company.main.shared;

import com.company.main.models.Hero;
import com.company.main.models.Player;
import com.company.main.ui.GUI;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * <h1>GameLogic</h1>
 * Klasa ta zawiera metody odpowiedzialne za logikę gry m.in
 * obliczają dokładnie ilość życia, obrażeń bohatera i przeciwnika
 * @author  Piotr Mróz
 * @since   2020-06-17
 * */
public class GameLogic {

    /**
     * Metoda pobiera treść wciśniętego przycisku i zwraca jego ID
     * zastosowano tu wyrażenie lambda
     * @param str String : treść wciśniętego przycisku
     * @return id przycisku
     */
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

    /**
     * Metoda pobiera treść wciśniętego przycisku i zwraca jego koszt zakupu bohatera
     * zastosowano tu wyrażenie lambda
     * @param str String : treść wciśniętego przycisku
     * @return koszt przycisku
     */
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

    /**
     * Metoda wyświetla postacie gracza po jego stronie pola bitwy
     * @param p Player : instancja gracza
     * @param g GUI : instancja klasy GUI
     */
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
                    //END TURN
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

    /**
     * Sprawdza czy gracz ma wystarczającą ilość złota na zakup postaci
     * -metoda wywoływana jest na początku każdej rundy
     * @param playerInstance Player : instancja gracza
     * @param ui GUI : instancja klasy GUI
     */
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

    /**
     * Metoda Przełącza przyciski na początku rundy na aktywne
     * @param playerInstance Player : instancja gracza
     * @param ui GUI : instancja klasy GUI
     */
    public static void toggleButtons(GUI ui, Player playerInstance){
        if(ui.isMyTurn){
            ui.toggleButtonsEnabled();
            checkIfEnoughGold(playerInstance, ui);
        }else{
            ui.toggleButtonsNotEnabled();
        }
    }

    /**
     * Metoda oblicza wartość obrażeń gracza iterując listę jego postaci
     * @param playerInstance Player : instancja gracza
     * @return obrazenia gracza w tej turze(INT)
     */
    public static int calculateDMG(Player playerInstance){
        int dmgThisTurn = 0;
        for(Hero x : playerInstance.enemyHeroesOnField){
            dmgThisTurn += x.Attack;
        }
        return Math.max(dmgThisTurn - playerInstance.defense, 0);
    }

    /**
     * Metoda oblicza wartość hp przeciwnika
     * @param playerInstance Player : instancja gracza
     * @return hp przeciwnika
     */
    public static int calculateEnemyHealth(Player playerInstance){
        AtomicInteger dmgThisTurn = new AtomicInteger();
        for(int x : playerInstance.heroesOnField){
            switch (x) {
                case 0 -> dmgThisTurn.addAndGet(3);
                case 1 -> dmgThisTurn.addAndGet(2);
                case 2 -> dmgThisTurn.addAndGet(1);
                case 3 -> dmgThisTurn.addAndGet(1);
                case 4 -> dmgThisTurn.addAndGet(4);
            }
        }
        return Math.max(dmgThisTurn.get() - playerInstance.enemyDefense, 0);
    }

    /**
     * Metoda oblicza wartość obrony gracza iterując listę jego postaci
     * @param playerInstance Player : instancja gracza
     * @return obrona gracza w tej turze
     */
    public static int calculateDefense(Player playerInstance){
        AtomicInteger myDefense = new AtomicInteger();
        for(int x : playerInstance.heroesOnField){
            switch (x) {
                case 0 -> myDefense.addAndGet(1);
                case 1 -> myDefense.addAndGet(2);
                case 2 -> myDefense.addAndGet(1);
                case 3 -> myDefense.addAndGet(5);
                case 4 -> myDefense.addAndGet(5);
            }
        }
        return Math.max(myDefense.get(), 0);
    }

    /**
     * Metoda oblicza wartość obrony przeciwnika iterując listę jego postaci
     * @param playerInstance Player : instancja gracza
     * @return obrona przeciwnika w tej turze
     */
    public static int calculateEnemyDefense(Player playerInstance) {
        int defenseThisTurn = 0;
        for (Hero x : playerInstance.enemyHeroesOnField) {
            defenseThisTurn += x.Defense;
        }
        return defenseThisTurn;
    }
}
