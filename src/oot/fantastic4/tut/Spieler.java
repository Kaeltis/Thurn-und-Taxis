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
    private List<Stadt> placedHouses = new LinkedList<Stadt>();
    private List<Stadt> hand = new LinkedList<Stadt>();
    private List<Stadt> route = new LinkedList<Stadt>();

    public Spieler(String name) {
        this.name = name;
        this.currentGame = Game.getInstance();
    }

    public void finishRoute() {
        if (route.size() >= 3) {

            for (Stadt stadt : route) {
                placeHouse(stadt);
            }

            deleteRoute();
            MainWindow.getInstance().outputLogln("Route abgeschlossen!");

            if (haeuser <= 0) {
                currentGame.setLastRound(true);
            }

            MainWindow.getInstance().setFinishRouteButtonStatus(false);

            refreshView();
        }
    }

    public void placeHouse(Stadt stadt) {
        if (!placedHouses.contains(stadt)) {
            placedHouses.add(stadt);
            haeuser--;
        }
    }

    public void removeCards() {

    }

    public void drawCardFromStack() {
        Stadt karte = currentGame.pollCard();
        hand.add(karte);
        refreshView();
        MainWindow.getInstance().outputLogln(karte + " gezogen!");
    }

    public void useBailiff() {
        if (!currentGame.hasUsedOfficial()) {
            MainWindow.getInstance().outputLogln("Amtmann benutzt!");
            currentGame.setUsedOfficial(true);
            currentGame.setUsingOfficial(true);

            currentGame.swapOpenCards();
        }
    }

    public void usePostillion() {
        if (!currentGame.hasUsedOfficial()) {
            int minKarten;

            if (currentGame.isLastStep())
                minKarten = 1;
            else
                minKarten = 2;

            if (hand.size() >= minKarten) {
                MainWindow.getInstance().outputLogln("Postillion benutzt!");
                currentGame.setUsedOfficial(true);
                currentGame.setUsingOfficial(true);

                currentGame.setPlaceRouteAllowed(true);
            } else {
                MainWindow.getInstance().outputLogln("Mindestens " + minKarten + " Karte(n) auf der Hand nötig!");
            }
        }
    }

    public void usePostmaster() {
        if (!currentGame.hasUsedOfficial()) {
            MainWindow.getInstance().outputLogln("Postmeister benutzt!");
            currentGame.setUsedOfficial(true);
            currentGame.setUsingOfficial(true);

            currentGame.setDrawAllowed(true);
        }
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
        refreshView();
    }

    public void drawCardFromAuslage(int index) {
        Stadt karte = currentGame.popAuslageCard(index);
        hand.add(karte);
        MainWindow.getInstance().outputLogln(karte + " gezogen!");
        refreshView();
    }

    public void refreshView() {
        MainWindow.getInstance().loadPlayerView(this);
    }

    public void deleteRoute() {
        for (Stadt karte : route) {
            Game.getInstance().addCard(karte);
        }
        route.clear();
        MainWindow.getInstance().outputLogln("Route auf den Ablagestapel gelegt.");
        refreshView();
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\nPunkte: " + getPoints() + "\n";
    }

    public String getPlacedHouses() {
        String output = "";

        for (Stadt stadt : placedHouses) {
            output += stadt + ",\n";
        }

        return output;
    }
}
