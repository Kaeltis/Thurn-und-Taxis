package oot.fantastic4.tut;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick on 15.12.13.
 */
public class Game {
    private static Game instance = new Game();
    private MainWindow mainWindow;

    private List<Spieler> mitspieler = new LinkedList<Spieler>();
    private Stapel kartenStapel = new Stapel();
    private List<Stadt> auslageKarten = new LinkedList<Stadt>();
    private UndirectedGraph<Stadt, DefaultEdge> mapGraph;


    private boolean gameRunning = true;
    private int currentPlayer = 0;
    private boolean drawAllowed = false;
    private boolean placeRouteAllowed = false;

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

    private Game() {
        mainWindow = MainWindow.getInstance();
        mapGraph = createMapGraph();
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

    public static Game getInstance() {
        return instance;
    }

    public void addPlayer(Spieler spieler) {
        mitspieler.add(spieler);
    }

    public Spieler selectWinner() {
        Spieler winner = null;

        for (Spieler spieler : mitspieler) {
            //if(spieler.getPoints() > winner.getPoints()) {
            //    winner = spieler;
            //}
        }

        return winner;
    }

    public void startGame() {
        mainWindow.outputLogln("Spiel Gestartet!");
        addOpenCards(6);
        loadCurrentPlayer();
    }

    private void addOpenCards(int anzahl) {

        for (int i = 1; i <= anzahl; i++) {
            auslageKarten.add(kartenStapel.pop());
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
        mainWindow.outputLogln(spieler.getName() + " ist an der Reihe.");
        mainWindow.loadPlayerView(spieler);
        mainWindow.outputLogln("Bitte Karte vom Stapel oder der Auslage ziehen.");
        setDrawAllowed(true);
    }

    public void endGame() {
        mainWindow.outputLogln("Spiel Beendet!");
    }

    public void giveBonus(Spieler spieler) {

    }

    public void swapOpenCards() {

    }

    public Stadt popCard() {
        return kartenStapel.pop();
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
            setPlaceRouteAllowed(false);
            player.addToRoute(stadt, false);
            // Eine Karte in Route
        } else if (route.size() == 1) {
            Stadt first = route.get(0);
            if (mapGraph.edgesOf(stadt).contains(first)) {
                Object[] options = {"Anfang", "Ende"};
                int selection = mainWindow.askMessage(options, "Karte wo anfügen?", "Frage");
                if (selection == JOptionPane.OK_OPTION) {
                    setPlaceRouteAllowed(false);
                    player.addToRoute(stadt, true);
                } else {
                    setPlaceRouteAllowed(false);
                    player.addToRoute(stadt, false);
                }
            } else {
                mainWindow.showMessage("Route nicht möglich!", "Nicht möglich!", JOptionPane.WARNING_MESSAGE);
            }
            // Mehrere Karten in Route
        } else {
            Stadt first = route.get(0);
            Stadt last = route.get(route.size() - 1);

            if (mapGraph.edgesOf(stadt).contains(first)) {
                setPlaceRouteAllowed(false);
                player.addToRoute(stadt, true);
            } else if (mapGraph.edgesOf(stadt).contains(last)) {
                setPlaceRouteAllowed(false);
                player.addToRoute(stadt, false);
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
    }

    public void setPlaceRouteAllowed(boolean status) {
        placeRouteAllowed = status;
    }

    public boolean isDrawAllowed() {
        return drawAllowed;
    }

    public boolean isPlaceRouteAllowed() {
        return placeRouteAllowed;
    }
}
