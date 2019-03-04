package edu.gatech.cs2340.spacetrader.viewmodels;

import java.util.HashMap;

import edu.gatech.cs2340.spacetrader.entity.Item;

public abstract class DisplayableSeller {
    private HashMap<Item, Integer> itemsMarketSells;

    public abstract HashMap<Item, Integer> stockSeller();

    public HashMap<Item, Integer> getItemsSold() {
        return itemsMarketSells;
    }

    public void remove(Item s) {
        
    }
}
