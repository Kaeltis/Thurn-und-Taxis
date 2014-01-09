package oot.fantastic4.tut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Patrick on 17.12.13.
 */
public class Bonus {
    private List<Integer> bonusEnde;
    private List<Integer> bonus5er;
    private List<Integer> bonus6er;
    private List<Integer> bonus7er;
    private List<Integer> bonusAlleLänder;
    private List<Integer> bonusBaden;
    private List<Integer> bonusSchweizTyrol;
    private List<Integer> bonusWürttembergHohenzollern;
    private List<Integer> bonusBöhmenSalzburg;
    private List<Integer> bonusBaiern;

    public Bonus() {
        bonusEnde = new ArrayList<Integer>();
        bonusEnde.add(1);

        bonus5er = new ArrayList<Integer>();
        Collections.addAll(bonus5er, 2, 1);

        bonus6er = new ArrayList<Integer>();
        Collections.addAll(bonus6er, 3, 2, 1);

        bonus7er = new ArrayList<Integer>();
        Collections.addAll(bonus7er, 4, 3, 2, 1);

        bonusAlleLänder = new ArrayList<Integer>();
        Collections.addAll(bonusAlleLänder, 6, 5, 4, 3);

        bonusBaden = new ArrayList<Integer>();
        Collections.addAll(bonusBaden, 3, 2, 1);

        bonusSchweizTyrol = new ArrayList<Integer>();
        Collections.addAll(bonusSchweizTyrol, 3, 2, 1);

        bonusWürttembergHohenzollern = new ArrayList<Integer>();
        Collections.addAll(bonusWürttembergHohenzollern, 3, 2, 1);

        bonusBöhmenSalzburg = new ArrayList<Integer>();
        Collections.addAll(bonusBöhmenSalzburg, 4, 3, 2);

        bonusBaiern = new ArrayList<Integer>();
        Collections.addAll(bonusBaiern, 5, 4, 3, 2);
    }

    public int getOneBonusEnde() {
        int bonus = 0;
        if (!bonusEnde.isEmpty()) {
            bonus = bonusEnde.get(0);
            bonusEnde.remove(0);
        }
        return bonus;
    }

    public int getOneBonus5er() {
        int bonus = 0;
        if (!bonus5er.isEmpty()) {
            bonus = bonus5er.get(0);
            bonus5er.remove(0);
        }
        return bonus;
    }

    public int getOneBonus6er() {
        int bonus = 0;
        if (!bonus6er.isEmpty()) {
            bonus = bonus6er.get(0);
            bonus6er.remove(0);
        }
        return bonus;
    }

    public int getOneBonus7er() {
        int bonus = 0;
        if (!bonus7er.isEmpty()) {
            bonus = bonus7er.get(0);
            bonus7er.remove(0);
        }
        return bonus;
    }

    public int getOneBonusAlleLaender() {
        int bonus = 0;
        if (!bonusAlleLänder.isEmpty()) {
            bonus = bonusAlleLänder.get(0);
            bonusAlleLänder.remove(0);
        }
        return bonus;
    }

    public int getOneBonusBaden() {
        int bonus = 0;
        if (!bonusBaden.isEmpty()) {
            bonus = bonusBaden.get(0);
            bonusBaden.remove(0);
        }
        return bonus;
    }

    public int getOneBonusSchweizTyrol() {
        int bonus = 0;
        if (!bonusSchweizTyrol.isEmpty()) {
            bonus = bonusSchweizTyrol.get(0);
            bonusSchweizTyrol.remove(0);
        }
        return bonus;
    }

    public int getOneBonusWuerttembergHohenzollern() {
        int bonus = 0;
        if (!bonusWürttembergHohenzollern.isEmpty()) {
            bonus = bonusWürttembergHohenzollern.get(0);
            bonusWürttembergHohenzollern.remove(0);
        }
        return bonus;
    }

    public int getOneBonusBoehmenSalzburg() {
        int bonus = 0;
        if (!bonusBöhmenSalzburg.isEmpty()) {
            bonus = bonusBöhmenSalzburg.get(0);
            bonusBöhmenSalzburg.remove(0);
        }
        return bonus;
    }

    public int getOneBonusBaiern() {
        int bonus = 0;
        if (!bonusBaiern.isEmpty()) {
            bonus = bonusBaiern.get(0);
            bonusBaiern.remove(0);
        }
        return bonus;
    }

}
