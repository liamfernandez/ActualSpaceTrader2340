package edu.gatech.cs2340.spacetrader.entity;

import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Inventory {
    private HashMap<Item, Integer> map;

    public Inventory() {
        map = new HashMap<>();
    }
    /**
     * Add item to inventory
     *
     * @param item
     */
    private void add(MockItem item) {
        add(item);
    }

    public void add(List<MockItem> itemsToAdd) {
        for (MockItem item : itemsToAdd) {
            add(item);
        }
    }

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
    public MockItem remove(MockItem item, int quantity) {
        //MockItem toReturn = new MockItem(item.getItemType(), item.getBuyingPrice(), item.getSellingPrice());
        int currQuantity = 0;
        if (map.containsKey(item.getItemType())) {
            currQuantity = map.get(item.getItemType());
        } else {
            return null;
        }
        if (currQuantity - quantity < 0) {
            return null;
        } else if (currQuantity - quantity == 0) {
            map.remove(item.getItemType());
            return item;
        } else {
            map.put(item.getItemType(), currQuantity - quantity);
            return item;
            //return toReturn;
        }

    }

    public MockItem remove(Item item, int quantity) {
        return remove(new MockItem(item, 0,0), quantity);
    }

    public boolean contains(MockItem i) {
        return map.containsKey(i.getItemType());
    }

    public boolean contains(Item i) { return map.containsKey(i); }

    /**
     * Get quantity of item in player's inventory
     *
     * @param i item
     * @return
     */
    public int getQuantity(MockItem i) {
        return getQuantity(i.getItemType());
    }

    public int getQuantity(Item item) {
        if (!contains(item)) {
            return 0;
        }
        return map.get(item);
    }
    public HashMap<Item, Integer> getMap() {
        return map;
    }
}
