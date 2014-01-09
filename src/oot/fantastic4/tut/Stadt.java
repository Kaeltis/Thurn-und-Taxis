package oot.fantastic4.tut;

/**
 * Created by kaeltis on 13.12.13.
 */
public enum Stadt {
    CARLSRUHE(Land.BADEN),
    FREIBURG(Land.BADEN),
    MANNHEIM(Land.BADEN),
    BASEL(Land.SCHWEIZ),
    ZÜRICH(Land.SCHWEIZ),
    INNSBRUCK(Land.TYROL),
    SIGMARINGEN(Land.WÜRTTEMBERG),
    STUTTGART(Land.HOHENZOLLERN),
    ULM(Land.HOHENZOLLERN),
    BUDWEIS(Land.BÖHMEN),
    PILSEN(Land.BÖHMEN),
    LINZ(Land.SALZBURG),
    SALZBURG(Land.SALZBURG),
    AUGSBURG(Land.BAIERN),
    INGOLSTADT(Land.BAIERN),
    KEMPTEN(Land.BAIERN),
    MÜNCHEN(Land.BAIERN),
    NÜRNBERG(Land.BAIERN),
    PASSAU(Land.BAIERN),
    REGENSBURG(Land.BAIERN),
    WÜRZBURG(Land.BAIERN),
    LODZ(Land.POLEN);

    private Land land;

    Stadt(Land land) {
        this.land = land;
    }

    public Land getLand() {
        return land;
    }

    public static int countStaedteOfLand(Land land) {
        int count = 0;
        for (Stadt stadt : Stadt.values()) {
            if (stadt.getLand() == land)
                count++;
        }
        return count;
    }

}
