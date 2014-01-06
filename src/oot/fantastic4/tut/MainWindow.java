package oot.fantastic4.tut;

import oot.fantastic4.tut.swing.CityButton;
import oot.fantastic4.tut.swing.ImagePanel;
import oot.fantastic4.tut.swing.NoEditTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by kaeltis on 13.12.13.
 */
public class MainWindow {
    private static MainWindow instance = new MainWindow();
    private Game mainGame;

    private JTextArea statusTextArea;
    private JButton ablagestapelButton;
    private JButton kartenstapelButton;
    private JPanel mapPanel;
    private JPanel mainPanel;
    private JButton amtmannButton;
    private JButton postmeisterButton;
    private JButton postillionButton;
    private JPanel handKartenPanel;
    private DefaultTableModel auslageTableModel;
    private DefaultTableModel handTableModel;
    private DefaultTableModel routeTableModel;
    private DefaultTableModel statsTableModel;
    private JTable auslageTable;
    private JTable routeTable;
    private JTable statsTable;
    private JTable handTable;
    private JButton endTurnButton;
    private JButton deleteCurrentRouteButton;
    private JButton finishRouteButton;

    private MainWindow() {

        kartenstapelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainGame.isDrawAllowed()) {
                    mainGame.setDrawAllowed(false);
                    mainGame.getCurrentPlayer().drawCardFromStack();
                    mainGame.continueAfterDraw();
                }
            }
        });

        auslageTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (mainGame.isDrawAllowed()) {
                    JTable table = (JTable) me.getSource();
                    Point p = me.getPoint();
                    int row = table.rowAtPoint(p);
                    if (me.getClickCount() == 2) {
                        mainGame.setDrawAllowed(false);
                        mainGame.getCurrentPlayer().drawCardFromAuslage(row);
                        mainGame.addOpenCards(1);
                        mainGame.continueAfterDraw();
                    }
                }
            }
        });

        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGame.nextTurn();
            }
        });

        deleteCurrentRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGame.getCurrentPlayer().deleteRoute();
            }
        });

        finishRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGame.getCurrentPlayer().finishRoute();
            }
        });
        amtmannButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGame.getCurrentPlayer().useBailiff();
            }
        });
        postmeisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGame.getCurrentPlayer().usePostmaster();
            }
        });
        postillionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGame.getCurrentPlayer().usePostillion();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Thurn und Taxis");
        frame.setContentPane(getInstance().mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.pack();
        //frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width - 150, screenSize.height - 150);
        frame.validate();

        frame.setVisible(true);

        // Game Setup
        getInstance().mainGame = Game.getInstance();
        getInstance().enterPlayers();
        getInstance().mainGame.startGame();
    }

    public void showMessage(String message, String title, int option) {
        JOptionPane.showMessageDialog(mainPanel, message, title, option);
    }

    public int askMessage(Object[] options, String message, String title) {
        return JOptionPane.showOptionDialog(mainPanel,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    private void createUIComponents() {
        // Nimbus look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            showMessage("Java Nimbus LookAndFeel nicht gefunden!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }

        // Map Background
        try {
            mapPanel = new ImagePanel("src/oot/fantastic4/tut/resources/thurnplan.jpg");
        } catch (IOException ex) {
            showMessage("Maphintergrund nicht gefunden!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }

        // Map Buttons
        mapPanel.setLayout(null);

        JButton mannheimButton = new CityButton(Stadt.MANNHEIM, 7, 8, mapPanel);
        JButton carlsruheButton = new CityButton(Stadt.CARLSRUHE, 2, 21, mapPanel);
        JButton freiburgButton = new CityButton(Stadt.FREIBURG, 1, 35, mapPanel);
        JButton baselButton = new CityButton(Stadt.BASEL, 1, 44, mapPanel);
        JButton zuerichButton = new CityButton(Stadt.ZÜRICH, 6, 47, mapPanel);
        JButton stuttgartButton = new CityButton(Stadt.STUTTGART, 11, 17, mapPanel);
        JButton sigmaringenButton = new CityButton(Stadt.SIGMARINGEN, 6, 30, mapPanel);
        JButton ulmButton = new CityButton(Stadt.ULM, 14, 28, mapPanel);
        JButton wuerzburgButton = new CityButton(Stadt.WÜRZBURG, 17, 5, mapPanel);
        JButton nuernbergButton = new CityButton(Stadt.NÜRNBERG, 24, 12, mapPanel);
        JButton ingolstadtButon = new CityButton(Stadt.INGOLSTADT, 23, 23, mapPanel);
        JButton augsburgButton = new CityButton(Stadt.AUGSBURG, 20, 31, mapPanel);
        JButton kemptenButton = new CityButton(Stadt.KEMPTEN, 16, 41, mapPanel);
        JButton innsbruckButton = new CityButton(Stadt.INNSBRUCK, 21, 47, mapPanel);
        JButton pilsenButton = new CityButton(Stadt.PILSEN, 34, 6, mapPanel);
        JButton regensburgButton = new CityButton(Stadt.REGENSBURG, 31, 19, mapPanel);
        JButton muenchenButton = new CityButton(Stadt.MÜNCHEN, 28, 36, mapPanel);
        JButton passauButton = new CityButton(Stadt.PASSAU, 37, 32, mapPanel);
        JButton lodzButton = new CityButton(Stadt.LODZ, 44, 5, mapPanel);
        JButton budweisButton = new CityButton(Stadt.BUDWEIS, 43, 18, mapPanel);
        JButton linzButton = new CityButton(Stadt.LINZ, 44, 32, mapPanel);
        JButton salzburgButton = new CityButton(Stadt.SALZBURG, 41, 46, mapPanel);


        // Auslage Tabelle
        auslageTableModel = new NoEditTableModel(new String[]{"Karte"}, 0);
        auslageTable = new JTable(auslageTableModel);

        // Hand Tabelle
        handTableModel = new NoEditTableModel(new String[]{"Karte"}, 0);
        handTable = new JTable(handTableModel);

        // Route Tabelle
        routeTableModel = new NoEditTableModel(new String[]{"Karte"}, 0);
        routeTable = new JTable(routeTableModel);

        // Stats Tabelle
        statsTableModel = new NoEditTableModel(new String[]{"Name", "Wert"}, 0);
        statsTable = new JTable(statsTableModel);

        // Log Text
        statusTextArea = new JTextArea();
        DefaultCaret caret = (DefaultCaret) statusTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public static MainWindow getInstance() {
        return instance;
    }

    public void outputLogln(String text) {
        outputLog(text);
        this.statusTextArea.append("\n");
    }

    public void outputLog(String text) {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        this.statusTextArea.append("[" + sdf.format(cal.getTime()) + "] " + text);
    }

    private void enterPlayers() {
        String spielername;
        int count = 1;

        do {
            spielername = (String) JOptionPane.showInputDialog(mainPanel, "Bitte den Namen des " + count + ". Spielers eingeben\nAbbrechen zum Fertigstellen.", "Spieler " + count);
            if (spielername != null) {
                mainGame.addPlayer(new Spieler(spielername));
                count++;
                outputLogln(spielername + " hinzugefügt.");
            }
        } while (spielername != null);

        if (count <= 2) {
            showMessage("Mindestens 2 Spieler notwendig!", "Fehler", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Mindestens 2 Spieler notwendig!");
        }
    }


    private void loadHandkarten(Spieler spieler) {
        // Tabelle leeren
        if (handTableModel.getRowCount() > 0) {
            for (int i = handTableModel.getRowCount() - 1; i > -1; i--) {
                handTableModel.removeRow(i);
            }
        }

        // Handkarten laden
        for (Stadt karte : spieler.getHand()) {
            handTableModel.addRow(new Object[]{karte});
        }
    }

    private void loadRoute(Spieler spieler) {
        // Tabelle leeren
        if (routeTableModel.getRowCount() > 0) {
            for (int i = routeTableModel.getRowCount() - 1; i > -1; i--) {
                routeTableModel.removeRow(i);
            }
        }

        // Route laden
        for (Stadt karte : spieler.getRoute()) {
            routeTableModel.addRow(new Object[]{karte});
        }
    }

    private void loadInfo(Spieler spieler) {
        // Tabelle leeren
        if (statsTableModel.getRowCount() > 0) {
            for (int i = statsTableModel.getRowCount() - 1; i > -1; i--) {
                statsTableModel.removeRow(i);
            }
        }

        statsTableModel.addRow(new Object[]{"Aktueller Spieler", spieler.getName()});
        statsTableModel.addRow(new Object[]{"Häuser", spieler.getHaeuser()});
        statsTableModel.addRow(new Object[]{"Bonus", spieler.getBonus()});
        statsTableModel.addRow(new Object[]{"Punkte", spieler.getPoints()});
    }

    public void loadAuslage(List<Stadt> auslage) {
        // Tabelle leeren
        if (auslageTableModel.getRowCount() > 0) {
            for (int i = auslageTableModel.getRowCount() - 1; i > -1; i--) {
                auslageTableModel.removeRow(i);
            }
        }

        // Route laden
        for (Stadt karte : auslage) {
            auslageTableModel.addRow(new Object[]{karte});
        }
    }

    public void loadPlayerView(Spieler spieler) {
        loadHandkarten(spieler);
        loadRoute(spieler);
        loadInfo(spieler);
    }

    public void setEndTurnButtonStatus(boolean status) {
        endTurnButton.setEnabled(status);
    }

    public void setFinishRouteButtonStatus(boolean status) {
        finishRouteButton.setEnabled(status);
    }

    public void setDeleteCurrentRouteButtonStatus(boolean status) {
        deleteCurrentRouteButton.setEnabled(status);
    }
}
