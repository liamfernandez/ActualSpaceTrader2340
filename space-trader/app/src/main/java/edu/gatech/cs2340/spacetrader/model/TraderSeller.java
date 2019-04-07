package edu.gatech.cs2340.spacetrader.model;

/**
 * This is the constructor for a trader seller
 */

public class TraderSeller extends DisplayableSeller {

    /**
     * Populates trader's list of items for sale
     * using the Store class
     */
    public TraderSeller() {
        itemsMarketSells = Store.getTraderItems();
    }
}
