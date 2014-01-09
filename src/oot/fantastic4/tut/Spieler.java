package oot.fantastic4.tut;

import javax.swing.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick on 15.12.13.
 */
public class Spieler {
    private String name;
    private Game currentGame;
    private MainWindow mainWindow;
    private int bonus = 0;
    private int haeuser = 20;
    private List<Stadt> placedHouses = new LinkedList<Stadt>();
    private List<Stadt> hand = new LinkedList<Stadt>();
    private List<Stadt> route = new LinkedList<Stadt>();
    private List<Bonus> gotBonus = new LinkedList<Bonus>();

    public Spieler(String name) {
        this.name = name;
        this.currentGame = Game.getInstance();
        this.mainWindow = MainWindow.getInstance();
    }

    public void finishRoute() {
        if (route.size() >= 3) {

            Object[] options = {"Eine Stadt pro Land", "Ein Land alle Städte"};
            int selection = mainWindow.askMessage(options, "Häuser wie setzen?", "Frage");

            if (selection == JOptionPane.OK_OPTION) { // Eine Stadt pro Land
                Land[] laender = getRouteLaender();
                Stadt[] choices;

                for (Land land : laender) {
                    choices = getRouteStaedteOfLandWithNoPlayerHouse(land);

                    if (choices.length > 0) {
                        Stadt auswahl = (Stadt) JOptionPane.showInputDialog(mainWindow.getMainPanel(), "Welches Stadt in " + land + "?",
                                "Frage", JOptionPane.QUESTION_MESSAGE, null,
                                choices,
                                choices[0]);

                        placeHouse(auswahl);
                    }
                }

            } else { // Ein Land alle Städte
                Land[] choices = getRouteLaender();

                Land auswahl = (Land) JOptionPane.showInputDialog(mainWindow.getMainPanel(), "Welches Land?",
                        "Frage", JOptionPane.QUESTION_MESSAGE, null,
                        choices,
                        choices[0]);

                for (Stadt stadt : route) {
                    if (stadt.getLand() == auswahl) {
                        placeHouse(stadt);
                    }
                }

            }

            collectBonus();

            deleteRoute();
            mainWindow.outputLogln("Route abgeschlossen!");

            if (haeuser <= 0) {
                currentGame.setLastRound(true);
            }

            mainWindow.setFinishRouteButtonStatus(false);

            refreshView();
        }
    }

    private void collectBonus() {
        // Spieler beendet Spiel
        if (haeuser <= 0)
            bonus += Bonus.BONUS_ENDE.getOne();

        // Routenlängen Bonus
        if (route.size() >= 7)
            bonus += Bonus.BONUS_ROUTE_7.getOne();
        else if (route.size() >= 6)
            bonus += Bonus.BONUS_ROUTE_6.getOne();
        else if (route.size() >= 5)
            bonus += Bonus.BONUS_ROUTE_5.getOne();

        // Alle Länder mit Haus
        if (!gotBonus.contains(Bonus.BONUS_ALLE_LAENDER)) {
            List<Land> laender = new ArrayList<Land>();
            for (Stadt stadt : placedHouses) {
                if (!laender.contains(stadt.getLand()))
                    laender.add(stadt.getLand());
            }
            //TODO: does this work?
            if (laender.containsAll(EnumSet.allOf(Land.class))) {
                bonus += Bonus.BONUS_ALLE_LAENDER.getOne();
                gotBonus.add(Bonus.BONUS_ALLE_LAENDER);
            }
        }

        // BONUS_BADEN
        if (!gotBonus.contains(Bonus.BONUS_BADEN)) {
            if (checkLandBonus(Land.BADEN)) {
                bonus += Bonus.BONUS_BADEN.getOne();
                gotBonus.add(Bonus.BONUS_BADEN);
            }
        }

        // BONUS_SCHWEIZ_TYROL
        if (!gotBonus.contains(Bonus.BONUS_SCHWEIZ_TYROL)) {
            if (checkLandBonus(Land.SCHWEIZ) && checkLandBonus(Land.TYROL)) {
                bonus += Bonus.BONUS_SCHWEIZ_TYROL.getOne();
                gotBonus.add(Bonus.BONUS_SCHWEIZ_TYROL);
            }
        }

        // BONUS_WUERTTEMBERG_HOHENZOLLERN
        if (!gotBonus.contains(Bonus.BONUS_WUERTTEMBERG_HOHENZOLLERN)) {
            if (checkLandBonus(Land.WÜRTTEMBERG) && checkLandBonus(Land.HOHENZOLLERN)) {
                bonus += Bonus.BONUS_WUERTTEMBERG_HOHENZOLLERN.getOne();
                gotBonus.add(Bonus.BONUS_WUERTTEMBERG_HOHENZOLLERN);
            }
        }

        // BONUS_BOEHMEN_SALZBURG
        if (!gotBonus.contains(Bonus.BONUS_BOEHMEN_SALZBURG)) {
            if (checkLandBonus(Land.BÖHMEN) && checkLandBonus(Land.SALZBURG)) {
                bonus += Bonus.BONUS_BOEHMEN_SALZBURG.getOne();
                gotBonus.add(Bonus.BONUS_BOEHMEN_SALZBURG);
            }
        }

        // BONUS_BAIERN
        if (!gotBonus.contains(Bonus.BONUS_BAIERN)) {
            if (checkLandBonus(Land.BAIERN)) {
                bonus += Bonus.BONUS_BAIERN.getOne();
                gotBonus.add(Bonus.BONUS_BAIERN);
            }
        }
    }

    private boolean checkLandBonus(Land land) {
        List<Stadt> staedte = new ArrayList<Stadt>();
        for (Stadt stadt : placedHouses) {
            if (stadt.getLand() == land && !staedte.contains(stadt)) {
                staedte.add(stadt);
            }
        }

        return Stadt.countStaedteOfLand(land) == staedte.size();
    }

    private Land[] getRouteLaender() {
        List<Land> laender = new ArrayList<Land>();

        for (Stadt stadt : route) {
            if (!laender.contains(stadt.getLand())) {
                laender.add(stadt.getLand());
            }
        }

        return laender.toArray(new Land[laender.size()]);
    }

    private Stadt[] getRouteStaedteOfLandWithNoPlayerHouse(Land land) {
        List<Stadt> staedte = new ArrayList<Stadt>();

        for (Stadt stadt : route) {
            if (land.isStadtInLand(stadt) && !staedte.contains(stadt) && !placedHouses.contains(stadt)) {
                staedte.add(stadt);
            }
        }

        return staedte.toArray(new Stadt[staedte.size()]);
    }

    public void placeHouse(Stadt stadt) {
        if (!placedHouses.contains(stadt)) {
            placedHouses.add(stadt);
            haeuser--;
        }
    }

    public void drawCardFromStack() {
        Stadt karte = currentGame.pollCard();
        hand.add(karte);

        refreshView();
        mainWindow.outputLogln(karte + " gezogen!");
    }

    public void useBailiff() {
        if (!currentGame.hasUsedOfficial()) {
            mainWindow.outputLogln("Amtmann benutzt!");
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
                mainWindow.outputLogln("Postillion benutzt!");
                currentGame.setUsedOfficial(true);
                currentGame.setUsingOfficial(true);

                currentGame.setPlaceRouteAllowed(true);
            } else {
                mainWindow.outputLogln("Mindestens " + minKarten + " Karte(n) auf der Hand nötig!");
            }
        }
    }

    public void usePostmaster() {
        if (!currentGame.hasUsedOfficial()) {
            mainWindow.outputLogln("Postmeister benutzt!");
            currentGame.setUsedOfficial(true);
            currentGame.setUsingOfficial(true);

            currentGame.setDrawAllowed(true);
        }
    }

    public int getBonus() {
        return bonus;
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

        mainWindow.outputLogln(stadt + " zur Route hinzugefügt.");
        refreshView();
    }

    public void drawCardFromAuslage(int index) {
        Stadt karte = currentGame.popAuslageCard(index);
        hand.add(karte);
        mainWindow.outputLogln(karte + " gezogen!");
        refreshView();
    }

    public void refreshView() {
        mainWindow.loadPlayerView(this);
    }

    public void deleteRoute() {
        for (Stadt karte : route) {
            Game.getInstance().addCard(karte);
        }
        route.clear();
        mainWindow.outputLogln("Route auf den Ablagestapel gelegt.");
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
