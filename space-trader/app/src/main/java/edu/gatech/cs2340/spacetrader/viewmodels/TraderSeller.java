package edu.gatech.cs2340.spacetrader.viewmodels;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.model.Store;

public class TraderSeller extends DisplayableSeller {
    private Planet planet;

    public TraderSeller(Planet p) {
        planet = p;
        itemsMarketSells = Store.getTraderItems();
    }
}
