package oot.fantastic4.tut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Patrick on 09.01.14.
 */
public enum Bonus {
    BONUS_ENDE(1),
    BONUS_ROUTE_5(2, 1),
    BONUS_ROUTE_6(3, 2, 1),
    BONUS_ROUTE_7(4, 3, 2, 1),
    BONUS_ALLE_LAENDER(6, 5, 4, 3),
    BONUS_BADEN(3, 2, 1),
    BONUS_SCHWEIZ_TYROL(3, 2, 1),
    BONUS_WUERTTEMBERG_HOHENZOLLERN(3, 2, 1),
    BONUS_BOEHMEN_SALZBURG(4, 3, 2),
    BONUS_BAIERN(5, 4, 3, 2);
    private List<Integer> chips;

    Bonus(Integer... werte) {
        chips = new ArrayList<Integer>();
        Collections.addAll(chips, werte);
    }

    public int getOne() {
        int bonus = 0;
        if (!chips.isEmpty()) {
            bonus = chips.get(0);
            chips.remove(0);
        }
        return bonus;
    }
}
