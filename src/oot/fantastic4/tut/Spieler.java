package oot.fantastic4.tut;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick on 15.12.13.
 */
public class Spieler {
    private String name;
    private Game currentGame;
    private Bonus bonus = new Bonus();
    private int haeuser = 20;
    private List<Stadt> hand = new LinkedList<Stadt>();
    private List<Stadt> route = new LinkedList<Stadt>();

    public Spieler(String name) {
        this.name = name;
        this.currentGame = Game.getInstance();
    }

    public void finishRoute() {

    }

    public void placeHouse(Stadt stadt) {

    }

    public void removeCards() {

    }

    public void drawCard() {
        Stadt karte = currentGame.popCard();
        hand.add(karte);
        MainWindow.getInstance().outputLogln(karte + " gezogen!");
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

    public int getBonus() {
        return bonus.getValue();
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return getBonus() - haeuser;
    }

    public List<Stadt> getHand() {
        return hand;
    }

    public List<Stadt> getRoute() {
        return route;
    }

    public int getHaeuser() {
        return haeuser;
    }

    public void addToRoute(Stadt stadt, boolean anfang) {
        hand.remove(stadt);

        if (anfang) {
            route.add(0, stadt);
        } else {
            route.add(stadt);
        }

        MainWindow.getInstance().outputLogln(stadt + " zur Route hinzugefügt.");
        MainWindow.getInstance().loadPlayerView(this);
    }
}
