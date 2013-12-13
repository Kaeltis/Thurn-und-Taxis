package oot.fantastic4.tut;

import javax.swing.*;

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
    private JButton handkarte1Button;
    private JPanel handKartenPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Thurn und Taxis");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
