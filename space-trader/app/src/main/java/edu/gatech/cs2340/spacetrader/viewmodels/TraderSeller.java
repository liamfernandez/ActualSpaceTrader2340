package edu.gatech.cs2340.spacetrader.viewmodels;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.model.Store;

public class TraderSeller extends DisplayableSeller {

    /**
     * Populates trader's list of items for sale
     * using the Store class
     */
    public TraderSeller() {
        itemsMarketSells = Store.getTraderItems();
    }
}
