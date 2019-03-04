package edu.gatech.cs2340.spacetrader.viewmodels;

import java.util.HashMap;

import edu.gatech.cs2340.spacetrader.entity.Item;
import android.util.Pair;

public abstract class DisplayableSeller {
    protected HashMap<Item, Pair<Integer, Double>> itemsMarketSells;

    public HashMap<Item, Pair<Integer, Double>> getItemsSold() {
        return itemsMarketSells;
    }

    public void remove(Item s) {
        Pair<Integer, Double> oldPair = itemsMarketSells.get(s);
        Integer newSupplyLevel = oldPair.first;
        newSupplyLevel--;
        Pair<Integer, Double> newPair = new Pair<>(newSupplyLevel, oldPair.second);
        itemsMarketSells.replace(s, newPair);
    }

    public boolean hasItem(Item s) {
        return itemsMarketSells.get(s).first > 0;
    }

    public int getQuantityForSale(Item item) {
        if (hasItem(item)) {
            return itemsMarketSells.get(item).first;
        } else {
            return 0;
        }
    }
}
