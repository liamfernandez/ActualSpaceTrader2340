package edu.gatech.cs2340.spacetrader.entity;

public class MockItem {

    private String name;
    private String basePrice;

    public MockItem(String name, String basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public String getBasePrice() {
        return basePrice;
    }
}
