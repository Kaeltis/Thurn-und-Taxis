package oot.fantastic4.tut;

import javax.swing.*;
import java.awt.*;

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        mapPanel = new JPanel();
        mapPanel.setLayout(null);

        JButton mannheimButton = new JButton("Mannheim");
        JButton carlsruheButton = new JButton("Carlsruhe");
        JButton freiburgButton = new JButton("Freiburg");
        JButton baselButton = new JButton("Basel");
        JButton zürichButton = new JButton("Zürich");
        JButton stuttgartButton = new JButton("Stuttgart");

        positionCity(mannheimButton, 4, 1);
        positionCity(carlsruheButton, 3, 10);
        positionCity(freiburgButton, 2, 17);
        positionCity(baselButton, 1, 20);
        positionCity(zürichButton, 5, 22);
        positionCity(stuttgartButton, 9, 8);

    }

    private void positionCity(JButton button, int x, int y) {
        mapPanel.add(button);

        //Insets insets = mapPanel.getInsets();
        Dimension size = button.getPreferredSize();

        x = map(x, 0, 50, 0, 800);
        y = map(y, 0, 25, 0, 600);

        button.setBounds(x, y, size.width, size.height);
    }

    private int map(int x, int in_min, int in_max, int out_min, int out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}
