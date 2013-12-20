package oot.fantastic4.tut.swing;

import oot.fantastic4.tut.Stadt;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kaeltis on 20.12.13.
 */
public class CityButton extends JButton {

    private Stadt stadt;

    public CityButton(Stadt stadt, int x, int y, JPanel map) {
        super(stadt.name());

        this.stadt = stadt;

        map.add(this);

        //Insets insets = mapPanel.getInsets();
        Dimension size = this.getPreferredSize();

        x = map(x, 0, 50, 0, 630);
        y = map(y, 0, 50, 0, 460);

        this.setBounds(x, y, size.width, size.height);
        //this.setOpaque(false);
        //this.setContentAreaFilled(false);
        //this.setBorderPainted(false);
    }

    private int map(int x, int in_min, int in_max, int out_min, int out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}
