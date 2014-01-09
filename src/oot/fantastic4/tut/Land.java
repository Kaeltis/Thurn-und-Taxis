package oot.fantastic4.tut;

import java.awt.*;

/**
 * Created by Patrick on 17.12.13.
 */
public enum Land {
    BADEN(new Color(0x5E00FF)),
    SCHWEIZ(new Color(0x00B3FF)),
    TYROL(new Color(0x003FFF)),
    WÜRTTEMBERG(new Color(0x0BA300)),
    HOHENZOLLERN(new Color(0x76B013)),
    BÖHMEN(new Color(0xFF8800)),
    SALZBURG(new Color(0xFF4F22)),
    POLEN(new Color(0x6D6D6D)),
    BAIERN(new Color(0x836235));

    private Color farbe;

    Land(Color farbe) {
        this.farbe = farbe;
    }

    public Color getFarbe() {
        return farbe;
    }

    public boolean isStadtInLand(Stadt stadt) {
        return stadt.getLand() == this;
    }
}
