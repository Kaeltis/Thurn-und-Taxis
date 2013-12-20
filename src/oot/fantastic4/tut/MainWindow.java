package oot.fantastic4.tut;

import oot.fantastic4.tut.swing.CityButton;
import oot.fantastic4.tut.swing.ImagePanel;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by kaeltis on 13.12.13.
 */
public class MainWindow {
    private JTextArea statusTextArea;
    private JButton karte2Button;
    private JButton karte1Button;
    private JButton karte4Button;
    private JButton karte3Button;
    private JButton karte6Button;
    private JButton karte5Button;
    private JButton ablagestapelButton;
    private JButton kartenstapelButton;
    private JPanel mapPanel;
    private JPanel mainPanel;
    private JButton amtmannButton;
    private JButton postmeisterButton;
    private JButton postillionButton;
    private JPanel handKartenPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Thurn und Taxis");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private void createUIComponents() {
        try {
            mapPanel = new ImagePanel("src/oot/fantastic4/tut/resources/thurnplan.jpg");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Maphintergrund nicht gefunden!", "Fehler", JOptionPane.CANCEL_OPTION);
        }

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
    }

}
