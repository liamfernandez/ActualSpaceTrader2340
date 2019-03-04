package edu.gatech.cs2340.spacetrader.entity;

import java.util.HashMap;

public class Inventory {
    private HashMap<Item, Integer> map;

    public void add(Item item, int quantity) {
        int currQuantity = 0;
        if (map.containsKey(item)) {
            currQuantity = map.get(item);
        }
        map.put(item, quantity + currQuantity);
    }

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
}
