package oot.fantastic4.tut;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import javax.swing.*;
import java.util.*;

/**
 * Created by Patrick on 15.12.13.
 */
public class Game {
    private static Game instance = new Game();
    private MainWindow mainWindow;
    private Bonus bonus = new Bonus();

    private List<Spieler> mitspieler = new LinkedList<Spieler>();
    private Queue<Stadt> kartenStapel;
    private List<Stadt> auslageKarten = new LinkedList<Stadt>();
    private UndirectedGraph<Stadt, DefaultEdge> mapGraph;

    private boolean gameRunning = true;
    private boolean lastRound = false;

    private int currentPlayer = 0;
    private boolean usedOfficial = false;
    private boolean usingOfficial = false;
    private boolean lastStep = false;

    private boolean drawAllowed = false;
    private boolean placeRouteAllowed = false;


    private Game() {
        mainWindow = MainWindow.getInstance();
        mapGraph = createMapGraph();
        kartenStapel = createStapel();

        shuffleStapel();
    }

    private void setUpBonus() {
    }

    private UndirectedGraph<Stadt, DefaultEdge> createMapGraph() {
        UndirectedGraph<Stadt, DefaultEdge> graph =
                new SimpleGraph<Stadt, DefaultEdge>(DefaultEdge.class);

        for (Stadt stadt : Stadt.values()) {
            graph.addVertex(stadt);
        }

        graph.addEdge(Stadt.MANNHEIM, Stadt.WÜRZBURG);
        graph.addEdge(Stadt.MANNHEIM, Stadt.STUTTGART);
        graph.addEdge(Stadt.MANNHEIM, Stadt.CARLSRUHE);

        graph.addEdge(Stadt.CARLSRUHE, Stadt.STUTTGART);
        graph.addEdge(Stadt.CARLSRUHE, Stadt.FREIBURG);

        graph.addEdge(Stadt.FREIBURG, Stadt.SIGMARINGEN);
        graph.addEdge(Stadt.FREIBURG, Stadt.BASEL);
        graph.addEdge(Stadt.FREIBURG, Stadt.ZÜRICH);

        graph.addEdge(Stadt.BASEL, Stadt.ZÜRICH);

        graph.addEdge(Stadt.STUTTGART, Stadt.WÜRZBURG);
        graph.addEdge(Stadt.STUTTGART, Stadt.NÜRNBERG);
        graph.addEdge(Stadt.STUTTGART, Stadt.INGOLSTADT);
        graph.addEdge(Stadt.STUTTGART, Stadt.ULM);
        graph.addEdge(Stadt.STUTTGART, Stadt.SIGMARINGEN);

        graph.addEdge(Stadt.SIGMARINGEN, Stadt.ULM);
        graph.addEdge(Stadt.SIGMARINGEN, Stadt.KEMPTEN);
        graph.addEdge(Stadt.SIGMARINGEN, Stadt.ZÜRICH);

        graph.addEdge(Stadt.ZÜRICH, Stadt.KEMPTEN);

        graph.addEdge(Stadt.WÜRZBURG, Stadt.NÜRNBERG);

        graph.addEdge(Stadt.ULM, Stadt.INGOLSTADT);
        graph.addEdge(Stadt.ULM, Stadt.AUGSBURG);
        graph.addEdge(Stadt.ULM, Stadt.KEMPTEN);

        graph.addEdge(Stadt.KEMPTEN, Stadt.AUGSBURG);
        graph.addEdge(Stadt.KEMPTEN, Stadt.INNSBRUCK);

        graph.addEdge(Stadt.NÜRNBERG, Stadt.PILSEN);
        graph.addEdge(Stadt.NÜRNBERG, Stadt.REGENSBURG);
        graph.addEdge(Stadt.NÜRNBERG, Stadt.INGOLSTADT);

        graph.addEdge(Stadt.INGOLSTADT, Stadt.REGENSBURG);
        graph.addEdge(Stadt.INGOLSTADT, Stadt.MÜNCHEN);
        graph.addEdge(Stadt.INGOLSTADT, Stadt.AUGSBURG);

        graph.addEdge(Stadt.AUGSBURG, Stadt.MÜNCHEN);
        graph.addEdge(Stadt.AUGSBURG, Stadt.INNSBRUCK);

        graph.addEdge(Stadt.INNSBRUCK, Stadt.MÜNCHEN);
        graph.addEdge(Stadt.INNSBRUCK, Stadt.SALZBURG);

        graph.addEdge(Stadt.PILSEN, Stadt.LODZ);
        graph.addEdge(Stadt.PILSEN, Stadt.BUDWEIS);
        graph.addEdge(Stadt.PILSEN, Stadt.REGENSBURG);

        graph.addEdge(Stadt.REGENSBURG, Stadt.PASSAU);
        graph.addEdge(Stadt.REGENSBURG, Stadt.MÜNCHEN);

        graph.addEdge(Stadt.MÜNCHEN, Stadt.PASSAU);
        graph.addEdge(Stadt.MÜNCHEN, Stadt.SALZBURG);

        graph.addEdge(Stadt.BUDWEIS, Stadt.LINZ);

        graph.addEdge(Stadt.PASSAU, Stadt.LINZ);
        graph.addEdge(Stadt.PASSAU, Stadt.SALZBURG);

        graph.addEdge(Stadt.LINZ, Stadt.SALZBURG);

        return graph;
    }

    private Queue<Stadt> createStapel() {
        Queue<Stadt> stapel = new LinkedList<Stadt>();

        for (Stadt stadt : Stadt.values()) {
            stapel.add(stadt);
            stapel.add(stadt);
            stapel.add(stadt);
        }

        return stapel;
    }

    private void shuffleStapel() {
        Collections.shuffle((LinkedList) kartenStapel);
    }

    public static Game getInstance() {
        return instance;
    }

    public void addPlayer(Spieler spieler) {
        mitspieler.add(spieler);
    }

    public Spieler selectWinner() {
        Spieler winner = mitspieler.get(0);

        for (Spieler spieler : mitspieler) {
            if (spieler.getPoints() > winner.getPoints()) {
                winner = spieler;
            }
        }

        return winner;
    }

    public void startGame() {
        mainWindow.outputLogln("Spiel Gestartet!");
        addOpenCards(6);
        loadCurrentPlayer();
    }

    public void addOpenCards(int anzahl) {

        for (int i = 1; i <= anzahl; i++) {
            auslageKarten.add(pollCard());
        }

        mainWindow.loadAuslage(auslageKarten);
    }

    public void loadCurrentPlayer() {
        if (gameRunning) {
            beginTurn(getCurrentPlayer());
        } else {
            endGame();
        }
    }

    public void beginTurn(Spieler spieler) {
        mainWindow.showMessage(spieler.getName() + " ist an der Reihe.", spieler.getName(), JOptionPane.INFORMATION_MESSAGE);
        mainWindow.loadPlayerView(spieler);
        setDrawAllowed(true);
    }

    public void endGame() {
        mainWindow.showMessage("Spiel beendet! Punkte: " + mitspieler, "Ende!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public void giveBonus(Spieler spieler) {

    }

    public void swapOpenCards() {
        for (Stadt karte : auslageKarten) {
            addCard(karte);
        }
        auslageKarten.clear();
        addOpenCards(6);

        setUsingOfficial(false);
    }

    public Stadt pollCard() {
        return kartenStapel.poll();
    }

    public void addCard(Stadt item) {
        kartenStapel.add(item);
    }

    public void selectCity(Stadt stadt) {
        if (isPlaceRouteAllowed()) {
            Spieler player = getCurrentPlayer();

            if (player.getHand().contains(stadt)) {
                checkRoute(stadt);
            } else {
                mainWindow.showMessage("Stadt nicht auf der Hand!", "Nicht möglich!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void checkRoute(Stadt stadt) {
        Spieler player = getCurrentPlayer();
        List<Stadt> route = player.getRoute();

        // Leere Route
        if (route.isEmpty()) {
            player.addToRoute(stadt, false);
            continueAfterRoutePlaced();
            // Eine Karte in Route
        } else if (route.size() == 1) {
            Stadt first = route.get(0);
            if (mapGraph.containsEdge(stadt, first)) {
                Object[] options = {"Anfang", "Ende"};
                int selection = mainWindow.askMessage(options, "Karte wo anfügen?", "Frage");
                if (selection == JOptionPane.OK_OPTION) {
                    player.addToRoute(stadt, true);
                    continueAfterRoutePlaced();
                } else {
                    player.addToRoute(stadt, false);
                    continueAfterRoutePlaced();
                }
            } else {
                mainWindow.showMessage("Route nicht möglich!", "Nicht möglich!", JOptionPane.WARNING_MESSAGE);
            }
            // Mehrere Karten in Route
        } else {
            Stadt first = route.get(0);
            Stadt last = route.get(route.size() - 1);

            if (mapGraph.containsEdge(stadt, first)) {
                player.addToRoute(stadt, true);
                continueAfterRoutePlaced();
            } else if (mapGraph.containsEdge(stadt, last)) {
                player.addToRoute(stadt, false);
                continueAfterRoutePlaced();
            } else {
                mainWindow.showMessage("Route nicht möglich!", "Nicht möglich!", JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    public Spieler getCurrentPlayer() {
        return mitspieler.get(currentPlayer);
    }

    public Stadt popAuslageCard(int index) {
        Stadt karte = auslageKarten.get(index);
        auslageKarten.remove(index);
        mainWindow.loadAuslage(auslageKarten);
        return karte;
    }

    public void setDrawAllowed(boolean status) {
        drawAllowed = status;
        if (status) {
            mainWindow.outputLogln("Bitte Karte vom Stapel oder der Auslage ziehen.");
        }
    }

    public void setPlaceRouteAllowed(boolean status) {
        placeRouteAllowed = status;
        mainWindow.setDeleteCurrentRouteButtonStatus(status);
        if (status) {
            mainWindow.outputLogln("Bitte Karte zur Route legen.");
        }
    }

    public boolean isDrawAllowed() {
        return drawAllowed;
    }

    public boolean isPlaceRouteAllowed() {
        return placeRouteAllowed;
    }

    public void continueAfterDraw() {
        if (!isUsingOfficial()) {
            setPlaceRouteAllowed(true);
        } else {
            setUsingOfficial(false);
        }
    }

    private void continueAfterRoutePlaced() {
        setPlaceRouteAllowed(false);
        setLastStep(true);

        if (!isUsingOfficial()) {
            mainWindow.outputLogln("Bitte Zug beenden, Route abschließen oder Amtsperson benutzen.");
            if (getCurrentPlayer().getRoute().size() >= 3)
                mainWindow.setFinishRouteButtonStatus(true);
            mainWindow.setEndTurnButtonStatus(true);
        } else {
            setUsingOfficial(false);
        }
    }

    public void nextTurn() {
        mainWindow.setFinishRouteButtonStatus(false);
        mainWindow.setEndTurnButtonStatus(false);

        setUsedOfficial(false);
        setLastStep(false);
        setNextPlayer();
        loadCurrentPlayer();
    }

    private void setNextPlayer() {
        int playercount = mitspieler.size();

        if (currentPlayer == playercount - 1) {
            if (lastRound) {
                gameRunning = false;
            }
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
    }

    public void setLastRound(boolean lastRound) {
        this.lastRound = lastRound;
    }

    public boolean hasUsedOfficial() {
        return usedOfficial;
    }

    public void setUsedOfficial(boolean usedOfficial) {
        this.usedOfficial = usedOfficial;
    }

    public boolean isUsingOfficial() {
        return usingOfficial;
    }

    public void setUsingOfficial(boolean usingOfficial) {
        this.usingOfficial = usingOfficial;
    }

    public boolean isLastStep() {
        return lastStep;
    }

    public void setLastStep(boolean lastStep) {
        this.lastStep = lastStep;
    }
}
