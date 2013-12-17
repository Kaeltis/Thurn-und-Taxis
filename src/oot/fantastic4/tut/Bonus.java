package oot.fantastic4.tut;

/**
 * Created by Patrick on 17.12.13.
 */
public class Bonus {
    private int streckenBonus;
    private int laenderBonus;
    private int extraBonus;

    public Bonus() {
        this.streckenBonus = 0;
        this.laenderBonus = 0;
        this.extraBonus = 0;
    }

    public int getStreckenBonus() {
        return streckenBonus;
    }

    public void setStreckenBonus(int streckenBonus) {
        this.streckenBonus = streckenBonus;
    }

    public int getLaenderBonus() {
        return laenderBonus;
    }

    public void setLaenderBonus(int laenderBonus) {
        this.laenderBonus = laenderBonus;
    }

    public int getExtraBonus() {
        return extraBonus;
    }

    public void setExtraBonus(int extraBonus) {
        this.extraBonus = extraBonus;
    }

    public int getBonus() {
        return streckenBonus + laenderBonus + extraBonus;
    }
}
