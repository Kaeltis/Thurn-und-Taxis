package oot.fantastic4.tut;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by Patrick on 17.12.13.
 */
public class Stapel extends Stack<Karte> {
    public void mischen() {
        Collections.shuffle(this);
    }

    public Stapel() {
        for (Stadt stadt : Stadt.values()) {
            this.add(new Karte(stadt));
            this.add(new Karte(stadt));
            this.add(new Karte(stadt));
        }
        mischen();
    }
}
