package oot.fantastic4.tut;

import sun.misc.Queue;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by Patrick on 17.12.13.
 */
public class Stapel extends Stack<Stadt> {
    public void mischen() {
        Collections.shuffle(this);
    }

    public Stapel() {
        for (Stadt stadt : Stadt.values()) {
            this.add(stadt);
            this.add(stadt);
            this.add(stadt);
        }
        mischen();
    }
}
