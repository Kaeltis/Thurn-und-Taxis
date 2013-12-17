package oot.fantastic4.tut;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick on 15.12.13.
 */
public class Spieler {
    private String name;
    private Game currentGame;
    private Bonus bonus = new Bonus();
    private int häuser = 20;
    private List<Stadt> hand = new LinkedList<Stadt>();

    public Spieler(String name, Game currentGame) {
        this.name = name;
        this.currentGame = currentGame;
    }

    public void finishRoute() {

    }

    public void placeHouse(Stadt stadt) {

    }

    public void removeCards() {

    }

    public void drawCard() {

    }

    public void placeCard() {

    }

    public void chooseOfficial() {

    }

    public void useBailiff() {

    }

    public void usePostillion() {

    }

    public void usePostmaster() {

    }

    public Bonus getBonus() {
        return bonus;
    }

    public String getName() {
        return name;
    }
}
