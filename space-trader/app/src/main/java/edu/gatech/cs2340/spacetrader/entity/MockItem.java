package edu.gatech.cs2340.spacetrader.entity;

public class MockItem {

    private String name;
    private int basePrice;

    public MockItem(String name, int basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
