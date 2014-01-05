package oot.fantastic4.tut;

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
    private Stadt[] offeneKarten = new Stadt[6];
    private boolean gameRunning = true;
    private int currentPlayer = 0;
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
        loadCurrentPlayer();
    }

    public void loadCurrentPlayer() {
        if (gameRunning) {
            Spieler player = mitspieler.get(currentPlayer);
            beginTurn(player);
        } else {
            endGame();
        }
    }

    public void beginTurn(Spieler spieler) {
        mainWindow.outputLogln(spieler.getName() + " ist an der Reihe");
        spieler.drawCard();
        mainWindow.loadPlayerView(spieler);
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
        Spieler player = mitspieler.get(currentPlayer);

        if (player.getHand().contains(stadt)) {
            mainWindow.showMessage("Stadt Gefunden", "Gefunden!");
        } else {
            mainWindow.showMessage("Stadt nicht auf der Hand!", "Fehler!");
        }
    }
}
