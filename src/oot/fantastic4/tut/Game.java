package oot.fantastic4.tut;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick on 15.12.13.
 */
public class Game {
    private List<Spieler> mitspieler = new LinkedList<Spieler>();

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
