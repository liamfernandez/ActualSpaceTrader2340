package edu.gatech.cs2340.spacetrader.entity;

import android.util.Log;
import android.util.Pair;

import java.util.HashMap;
import java.util.Random;

public class MockItem {

    private String name;
    private int sellingPrice;
    private int buyingPrice;
    private int basePrice;
    private int MTLP;
    private int MTLU;
    private int TTP;
    private int IPL;
    private int var;
    private Resources IE;
    private Resources CR;
    private Resources ER;
    private int MTL;
    private int MTH;

    public MockItem(String name, int MTLP, int MTLU, int TTP,  int basePrice, int IPL, int var, Resources IE, Resources CR, Resources ER, int MTL, int MTH, int buyingPrice, int sellingPrice) {
        this.name = name;
        this.basePrice = basePrice;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.IPL = IPL;
        this.var = var;
        this.IE = IE;
        this.CR = CR;
        this.ER = ER;
        this.MTL = MTL;
        this.MTH = MTH;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public MockItem(MockItem item) {
        this(item.name, item.MTLP, item.MTLU, item.TTP, item.basePrice, item.IPL, item.var, item.IE, item.CR, item.ER, item.MTL, item.MTH, item.buyingPrice, item.sellingPrice);
    }


    public boolean isSellable(Planet planet) {
        return planet.getTechLevel().getLevel() >= MTLP;
    }

    public void setBuyingPrice(int price) {
        buyingPrice = price;
    }

    public void setSellingPrice(int price) {
        sellingPrice = price;
    }
    public String getName() {
        return name;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }
    public int getBasePrice() {
        return basePrice;
    }

    public int getMTLP() {
        return MTLP;
    }

    public int getMTLU() {
        return MTLU;
    }

    public int getTTP() {
        return TTP;
    }

    public int getIPL() {
        return IPL;
    }

    public int getVar() {
        return var;
    }

    public Resources getIE() {
        return IE;
    }

    public Resources getCR() {
        return CR;
    }

    public Resources getER() {
        return ER;
    }

    public int getMTL() {
        return MTL;
    }

    public int getMTH() {
        return MTH;
    }

    public int calcBuyingPrice(Planet planet) {
        double price = basePrice;

        Random random = new Random();

        price = price + IPL * planet.getTechLevel().getLevel();


        if (IE == planet.getResource()) {
            price *= 3.0;
        } else if (CR == planet.getResource()) {
            price *= 1.0/3.0;
        } else if (ER == planet.getResource()) {
            price *= 1.5;
        }

        double randomAdjustmentPercentage = random.nextDouble() * var * (2.0 / 100.00);
        Log.d("calcInfo", name + " randomadj: " + (randomAdjustmentPercentage));
        Log.d("calcInfo", name + " price: " + (price));
        Log.d("calcInfo", name + " base price: " + (basePrice));

        Log.d("calcInfo", name + "calculated to be bought for" + (price * (1+randomAdjustmentPercentage)));

        return (int) (price * (1.0 + randomAdjustmentPercentage));

    }

    public int calcSellingPrice() {
        double min = MTL;
        double max = MTH;

        double range = max - min;

        Random random = new Random();
        double priceAdded = random.nextDouble() * range;

        return (int) (min + priceAdded);
    }

    @Override
    public boolean equals(Object object) {
        try {
            return this.name.equals(((MockItem)object).getName());
        } catch (NullPointerException e) {
            return false;
        }
    }
}
