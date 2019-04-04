package edu.gatech.cs2340.spacetrader.entity;

import android.util.Log;
import android.util.Pair;

import java.util.HashMap;
import java.util.Random;

/**
 * @author squad
 * @version 0.01
 */
public class MockItem {

    private int sellingPrice;
    private int buyingPrice;
    public Item item;


    /**
     * constructor for a mock item
     * @param item what it is
     * @param buyingPrice the buy price
     * @param sellingPrice the sell price
     */
    public MockItem(Item item, int buyingPrice, int sellingPrice) {
        this.item = item;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    /**
     * constructor with one arg
     * @param item the item type
     */
    public MockItem(MockItem item) {
        this(item.item, item.buyingPrice, item.sellingPrice);
    }


    /**
     * Checks if you can sell an item
     * @param planet the planet you are on
     * @return if the planet has the right tech to sell the item
     */
    public boolean isSellable(Planet planet) {
        return planet.getTechLevel().getLevel() >= item.getMTLP();
    }

    /**
     * sets the buying price
     * @param price the price
     */
    public void setBuyingPrice(int price) {
        buyingPrice = price;
    }

    /**
     * sets the selling price
     * @param price the price
     */
    public void setSellingPrice(int price) {
        sellingPrice = price;
    }

    /**
     *
     * @return the item name
     */
    public String getName() {
        return item.getName();
    }

    /**
     *
     * @return the item type
     */
    public Item getItemType() {
        return item;
    }

    /**
     *
     * @return the selling price
     */
    public int getSellingPrice() {
        return sellingPrice;
    }

    /**
     *
     * @return the buying price
     */
    public int getBuyingPrice() {
        return buyingPrice;
    }


    /**
     *
     * @return the base price
     */
    public int getBasePrice() {
        return item.getBasePrice();
    }

    /**
     *
     * @return the minimum tech level required
     */
    public int getMTLP() {
        return item.getMTLP();
    }

    /**
     *
     * @return the MTLU
     */
    public int getMTLU() {
        return item.getMTLU();
    }

    /**
     *
     * @return the TTP
     */
    public int getTTP() {
        return item.getTTP();
    }

    /**
     *
     * @return the IPL
     */
    public int getIPL() {
        return item.getIPL();
    }

    /**
     *
     * @return the var
     */
    public int getVar() {
        return item.getVar();
    }

    /**
     *
     * @return the IE
     */
    public Resources getIE() {
        return item.getIE();
    }

    /**
     *
     * @return the CR
     */
    public Resources getCR() {
        return item.getCR();
    }

    /**
     *
     * @return the ER
     */
    public Resources getER() {
        return item.getER();
    }

    /**
     *
     * @return the MTL
     */
    public int getMTL() {
        return item.getMTL();
    }

    /**
     *
     * @return the MTH
     */
    public int getMTH() {
        return item.MTH;
    }

    /**
     *
     * @param planet the planet you're on
     * @return the buying price dependent on the planet
     */
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

    /**
     *
     * @return the selling price
     */
    public int calcSellingPrice() {
        double min = item.getMTL();
        double max = item.getMTH();

        double range = max - min;

        Random random = new Random();
        double priceAdded = random.nextDouble() * range;

        return (int) (min + priceAdded);
    }

    /**
     *
     * @param object the object
     * @return if it is equals or not
     */
    @Override
    public boolean equals(Object object) {
        try {
            return this.item == ((MockItem) object).item;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
