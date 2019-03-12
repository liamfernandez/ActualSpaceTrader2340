package edu.gatech.cs2340.spacetrader.entity;

import android.util.Log;
import android.util.Pair;

import java.util.HashMap;
import java.util.Random;

public class MockItem {

    private int sellingPrice;
    private int buyingPrice;
    public Item item;


    public MockItem(Item item, int buyingPrice, int sellingPrice) {
        this.item = item;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public MockItem(MockItem item) {
        this(item.item, item.buyingPrice, item.sellingPrice);
    }


    public boolean isSellable(Planet planet) {
        return planet.getTechLevel().getLevel() >= item.getMTLP();
    }

    public void setBuyingPrice(int price) {
        buyingPrice = price;
    }

    public void setSellingPrice(int price) {
        sellingPrice = price;
    }

    public String getName() {
        return item.getName();
    }

    public Item getItemType() {
        return item;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }
    public int getBasePrice() {
        return item.getBasePrice();
    }

    public int getMTLP() {
        return item.getMTLP();
    }

    public int getMTLU() {
        return item.getMTLU();
    }

    public int getTTP() {
        return item.getTTP();
    }

    public int getIPL() {
        return item.getIPL();
    }

    public int getVar() {
        return item.getVar();
    }

    public Resources getIE() {
        return item.getIE();
    }

    public Resources getCR() {
        return item.getCR();
    }

    public Resources getER() {
        return item.getER();
    }

    public int getMTL() {
        return item.getMTL();
    }

    public int getMTH() {
        return item.MTH;
    }

    public int calcBuyingPrice(Planet planet) {
        double price = item.getBasePrice();

        Random random = new Random();

        price = price + item.getIPL() * planet.getTechLevel().getLevel();


        if (item.getIE() == planet.getResource()) {
            price *= 3.0;
        } else if (item.getCR() == planet.getResource()) {
            price *= 1.0/3.0;
        } else if (item.getER() == planet.getResource()) {
            price *= 1.5;
        }

        double randomAdjustmentPercentage = random.nextDouble() * getVar() * (2.0 / 100.00);
        Log.d("calcInfo", item.getName() + " randomadj: " + (randomAdjustmentPercentage));
        Log.d("calcInfo", item.getName() + " price: " + (price));
        Log.d("calcInfo", item.getName() + " base price: " + (item.getBasePrice()));

        Log.d("calcInfo", item.getName() + "calculated to be bought for" + (price * (1+randomAdjustmentPercentage)));

        return (int) (price * (1.0 + randomAdjustmentPercentage));

    }

    public int calcSellingPrice() {
        double min = item.getMTL();
        double max = item.getMTH();

        double range = max - min;

        Random random = new Random();
        double priceAdded = random.nextDouble() * range;

        return (int) (min + priceAdded);
    }

    @Override
    public boolean equals(Object object) {
        try {
            return this.item == ((MockItem) object).item;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
