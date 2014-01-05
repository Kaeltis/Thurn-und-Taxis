package oot.fantastic4.tut.swing;

import oot.fantastic4.tut.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Patrick on 05.01.14.
 */
public class MapClickHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        CityButton button = (CityButton) e.getSource();
        Game.getInstance().selectCity(button.getStadt());
    }
}
