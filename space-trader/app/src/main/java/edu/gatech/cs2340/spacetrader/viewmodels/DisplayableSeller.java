package edu.gatech.cs2340.spacetrader.viewmodels;

import java.util.HashMap;

import edu.gatech.cs2340.spacetrader.entity.Item;
import android.util.Pair;

public abstract class DisplayableSeller {
    protected HashMap<Item, Pair<Integer, Double>> itemsMarketSells;

    public HashMap<Item, Pair<Integer, Double>> getItemsSold() {
        return itemsMarketSells;
    }

    /**
     * Getter method which returns items for sale
     *
     * @return Hash map where key is Item, value is Pair(quantity, price per item);
     */
    public HashMap<Item, Pair<Integer, Double>> getItemsForSale() {
        return itemsMarketSells;
    }

    /**
     * Removes one item from seller's for sale list
     *
     * Eg. if 4 of Item s are for sale, only 3 will be
     * after calling this method
     *
     * @param s Item desired to remove one of
     */
    public void remove(Item s) {
        Pair<Integer, Double> oldPair = itemsMarketSells.get(s);
        Integer newSupplyLevel = oldPair.first;
        newSupplyLevel--;
        Pair<Integer, Double> newPair = new Pair<>(newSupplyLevel, oldPair.second);
        itemsMarketSells.replace(s, newPair);
    }

    /**
     *
     * @param s item to check if is for sale
     * @return true if item s is for sale; false otherwise
     */
    public boolean hasItem(Item s) {
        return itemsMarketSells.get(s).first > 0;
    }

    /**
     * Get quantity of item Item for sale
     *
     * @param item Item desired
     * @return Quantity for sale
     */
    public int getQuantityForSale(Item item) {
        if (hasItem(item)) {
            return itemsMarketSells.get(item).first;
        } else {
            return 0;
        }
    }

    /**
     * Gets the sale price of the item item
     *
     * If the item is not for sale, will return -1
     *
     * @param item Item to get price
     * @return -1 if item not for sale. Price otherwise
     */
    public double getPrice(Item item) {
        if (itemsMarketSells.containsKey(item)) {
            return itemsMarketSells.get(item).second;
        } else {
            return -1;
        }
    }
}
