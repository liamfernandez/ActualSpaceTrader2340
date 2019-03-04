package edu.gatech.cs2340.spacetrader.entity;

import java.util.HashMap;

public class Inventory {
    private HashMap<Item, Integer> map;

    public Inventory() {
        map = new HashMap<>();
    }
    /**
     * Add item to inventory
     *
     * @param item
     * @param quantity how many items?
     */
    public void add(Item item, int quantity) {
        int currQuantity = 0;
        if (map.containsKey(item)) {
            currQuantity = map.get(item);
        }
        map.put(item, quantity + currQuantity);
    }

    /**
     * Will remove quantity of items from players inventory
     *
     * If player doesn't have sufficient stock to remove
     * all of quantity, no removals will occur.
     *
     * @param item Item to remove
     * @param quantity Quantity of item to remove
     * @return null if no removal occurred
     */
    public Item remove(Item item, int quantity) {
        int currQuantity = 0;
        if (map.containsKey(item)) {
            currQuantity = map.get(item);
        } else {
            return null;
        }
        if (currQuantity - quantity < 0) {
            return null;
        } else {
            map.put(item, currQuantity - quantity);
            return item;
        }

    }

    public boolean contains(Item i) {
        return map.containsKey(i);
    }

    /**
     * Get quantity of item in player's inventory
     *
     * @param i item
     * @return
     */
    public int getQuantity(Item i) {
        if (!contains(i)) {
            return 0;
        }
        return map.get(i);
    }
}
