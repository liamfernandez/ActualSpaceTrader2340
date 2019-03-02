package edu.gatech.cs2340.spacetrader.entity;

public enum Item {
    WATER("water",0, 0, 2, 30, 3, 4, "DROUGHT",
            "LOTSOFWATER", "DESERT", 30, 50),
    FURS("furs",0, 0, 0, 250, 10, 10, "COLD",
            "RICHFAUNA", "LIFELESS", 230, 280),
    FOOD("food",1, 0, 1, 100, 5, 5, "CROPFAIL",
            "RICHSOIL" ,"POORSOIL", 90, 160),
    ORE("ore",2, 2, 3, 350, 20, 10, "WAR",
            "MINERALRICH", "MINERALPOOR", 350, 420),
    GAMES("games",3, 1, 6, 250, -10, 5, "BOREDOM",
            "ARTISTIC", "Never", 160, 270),
    FIREARMS("firearms", 3, 1, 5, 1250, -75, 100, "WAR",
            "WARLIKE", "Never", 600, 1100),
    MEDICINE("medicine", 4, 1, 6, 650, -20, 10, "PLAGUE",
            "LOTSOFHERBS", "Never", 400, 700),
    MACHINES("machines", 4, 3, 5, 900, -30, 5, "LACKOFWORKERS",
            "Never", "Never", 600, 800),
    NARCOTICS("narcotics", 5, 0, 5, 3500, -125, 150, "BOREDOM",
            "WEIRDMUSHROOMS", "Never", 2000, 3000),
    ROBOTS("robots", 6, 4, 7, 5000, -150, 100, "LACKOFWORKERS",
            "Never", "Never", 3500, 5000);

    String name;
    int MTLP;
    int MTLU;
    int TTP;
    int basePrice;
    int IPL;
    int var;
    String IE;
    String CR;
    String ER;
    int MTL;
    int MTH;

    private Item(String name, int MTLP, int MTLU, int TTP, int basePrice,
                 int IPL,
                 int var
            , String IE, String CR, String ER, int MTL, int MTH) {
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

    public int getMTLP() {
        return MTLP;
    }

    public void setMTLP(int MTLP) {
        this.MTLP = MTLP;
    }

    public int getMTLU() {
        return MTLU;
    }

    public void setMTLU(int MTLU) {
        this.MTLU = MTLU;
    }

    public int getTTP() {
        return TTP;
    }

    public void setTTP(int TTP) {
        this.TTP = TTP;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getIPL() {
        return IPL;
    }

    public void setIPL(int IPL) {
        this.IPL = IPL;
    }

    public int getVar() {
        return var;
    }

    public void setVar(int var) {
        this.var = var;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getCR() {
        return CR;
    }

    public void setCR(String CR) {
        this.CR = CR;
    }

    public String getER() {
        return ER;
    }

    public void setER(String ER) {
        this.ER = ER;
    }

    public int getMTL() {
        return MTL;
    }

    public void setMTL(int MTL) {
        this.MTL = MTL;
    }

    public int getMTH() {
        return MTH;
    }

    public void setMTH(int MTH) {
        this.MTH = MTH;
    }
}