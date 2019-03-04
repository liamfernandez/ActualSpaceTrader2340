package edu.gatech.cs2340.spacetrader.viewmodels;

import android.util.Pair;
import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.model.Store;

import java.util.HashMap;

public class MarketSeller extends DisplayableSeller{
     private Planet planet;

    /**
     * Populates market with items legal to sell on planet
     * at prices appropriate for planet
     *
     * @param p Planet of the seller
     */
     public MarketSeller(Planet p) {
         planet = p;
         itemsMarketSells = Store.getMarketItems(planet);
     }

}