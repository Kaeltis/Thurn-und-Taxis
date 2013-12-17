package oot.fantastic4.tut;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick on 15.12.13.
 */
public class Game {
    private List<Spieler> mitspieler = new LinkedList<Spieler>();
    private Stapel kartenStapel = new Stapel();
    private Karte[] offeneKarten = new Karte[6];
    private int bonusEnde;
    private int bonus5er;
    private int bonus6er;
    private int bonus7er;
    private int bonusAlleLänder;
    private int bonusBaden;
    private int bonusSchweizTyrol;
    private int bonusWürttembergHohenzollern;
    private int bonusBöhmenSalzburg;
    private int bonusBaiern;

    public Game(Spieler spieler, Spieler... weitereSpieler) {
        mitspieler.add(spieler);
        Collections.addAll(mitspieler, weitereSpieler);
    }

    public Spieler selectWinner() {
        return null;
    }

    public int calcPoints(Spieler spieler) {
        return 0;
    }

    public void startGame() {

    }

    public void endGame() {

    }

    public void giveBonus(Spieler spieler) {

    }

    public void swapOpenCards() {

    }
}
