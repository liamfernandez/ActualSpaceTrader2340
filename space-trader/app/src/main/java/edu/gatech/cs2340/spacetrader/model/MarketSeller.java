package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.model.DisplayableSeller;
import edu.gatech.cs2340.spacetrader.model.Store;

/**
 *
 */
public class MarketSeller extends DisplayableSeller {
     //private Planet planet;

    /**
     * Populates market with items legal to sell on planet
     * at prices appropriate for planet
     *
     * @param p Planet of the seller
     */
     public MarketSeller(Planet p) {
         Planet planet = p;
         itemsMarketSells = Store.getMarketItems(planet);
     }

}
