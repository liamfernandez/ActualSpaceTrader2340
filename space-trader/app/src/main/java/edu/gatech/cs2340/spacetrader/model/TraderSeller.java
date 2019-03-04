package edu.gatech.cs2340.spacetrader.model;

public class TraderSeller extends DisplayableSeller {

    /**
     * Populates trader's list of items for sale
     * using the Store class
     */
    public TraderSeller() {
        itemsMarketSells = Store.getTraderItems();
    }
}
