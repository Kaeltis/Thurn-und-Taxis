package oot.fantastic4.tut;

import java.util.LinkedList;
import java.util.List;

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

}
