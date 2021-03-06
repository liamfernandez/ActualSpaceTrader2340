package edu.gatech.cs2340.spacetrader.entity;

/**
 * item enum
 */
public enum  Item {

    WATER("water",0, 0, 2, 30, 3, 4, Resources.DROUGHT,
            Resources.LOTSOFWATER, Resources.DESERT, 30, 50),
    FURS("furs",0, 0, 0, 250, 10, 10, Resources.COLD,
            Resources.RICHFAUNA, Resources.LIFELESS, 230, 280),
    FOOD("food",1, 0, 1, 100, 5, 5, Resources.CROPFAIL,
            Resources.RICHSOIL ,Resources.POORSOIL, 90, 160),
    ORE("ore",2, 2, 3, 350, 20, 10, Resources.WAR,
            Resources.MINERALRICH, Resources.MINERALPOOR, 350, 420),
    GAMES("games",3, 1, 6, 250, -10, 5, Resources.BOREDOM,
            Resources.ARTISTIC, null, 160, 270),
    FIREARMS("firearms", 3, 1, 5, 1250, -75, 100, Resources.WAR,
            Resources.WARLIKE, null, 600, 1100),
    MEDICINE("medicine", 4, 1, 6, 650, -20, 10, Resources.PLAGUE,
            Resources.LOTSOFHERBS, null, 400, 700),
    MACHINES("machines", 4, 3, 5, 900, -30, 5, Resources.LACKOFWORKERS,
            null, null, 600, 800),
    NARCOTICS("narcotics", 5, 0, 5, 3500, -125, 150, Resources.BOREDOM,
            Resources.WEIRDMUSHROOMS, null, 2000, 3000),
    ROBOTS("robots", 6, 4, 7, 5000, -150, 100, Resources.LACKOFWORKERS,
            null, null, 3500, 5000);

        final String name;
        final int MTLP;
        final int MTLU;
        final int TTP;
        final int basePrice;
        final int IPL;
        final int var;
        final Resources IE;
        final Resources CR;
        final Resources ER;
        final int MTL;
        final int MTH;

    /**
     * Item Constructor
     * @param name name
     * @param MTLP MTLP
     * @param MTLU MTLU
     * @param TTP TTP
     * @param basePrice basePrice
     * @param IPL IPL
     * @param var var
     * @param IE IE
     * @param CR CR
     * @param ER ER
     * @param MTL MTL
     * @param MTH MTH
     */
    Item(String name, int MTLP, int MTLU, int TTP, int basePrice,
                 int IPL,
                 int var
            , Resources IE, Resources CR, Resources ER, int MTL, int MTH) {
        this.name = name;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.basePrice = basePrice;
        this.IPL = IPL;
        this.var = var;
        this.IE = IE;
        this.CR = CR;
        this.ER = ER;
        this.MTL = MTL;
        this.MTH = MTH;
    }

    /**
     *
     * @return mtlp
     */
    public int getMTLP() {
        return MTLP;
    }


//    public void setMTLP(int MTLP) {
//        this.MTLP = MTLP;
//    }

    /**
     *
     * @return mtlu
     */
    public int getMTLU() {
        return MTLU;
    }


//    public void setMTLU(int MTLU) {
//        this.MTLU = MTLU;
//    }

    /**
     *
     * @return ttp
     */
    public int getTTP() {
        return TTP;
    }


//    public void setTTP(int TTP) {
//        this.TTP = TTP;
//    }

    /**
     *
     * @return basePrice
     */
    public int getBasePrice() {
        return basePrice;
    }


//    public void setBasePrice(int basePrice) {
//        this.basePrice = basePrice;
//    }

    /**
     *
     * @return IPL
     */
    public int getIPL() {
        return IPL;
    }


//    public void setIPL(int IPL) {
//        this.IPL = IPL;
//    }

    /**
     *
     * @return var
     */
    public int getVar() {
        return var;
    }

//    public void setVar(int var) {
//        this.var = var;
//    }

    /**
     *
     * @return IE
     */
    public Resources getIE() {
        return IE;
    }

//    public void setIE(Resources IE) {
//        this.IE = IE;
//    }

    /**
     *
     * @return CR
     */
    public Resources getCR() {
        return CR;
    }

//    public void setCR(Resources CR) {
//        this.CR = CR;
//    }

    /**
     *
     * @return ER
     */
    public Resources getER() {
        return ER;
    }


   // public void setER(Resources ER) {
//        this.ER = ER;
//    }

    /**
     *
     * @return MTL
     */
    public int getMTL() {
        return MTL;
    }

//    public void setMTL(int MTL) {
//        this.MTL = MTL;
//    }

    /**
     * gets the MTH
     * @return MTH
     */
    public int getMTH() {
        return MTH;
    }

    //public void setMTH(int MTH) {
//        this.MTH = MTH;
//    }

    /**
     * gets the name
     * @return name
     */
    public String getName() {
        return name;
    }
}