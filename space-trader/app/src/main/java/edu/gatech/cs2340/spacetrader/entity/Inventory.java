package edu.gatech.cs2340.spacetrader.entity;

import java.util.HashMap;

/**
 *
 */
public class Inventory {
    private final HashMap<Item, Integer> map;

    /**
     *
     */
    public Inventory() {
        map = new HashMap<>();
    }

    /**
     *
     * @param item item to add
     * @param quantity amount of item
     */
    public void add(Item item, int quantity) {
        int currQuantity = 0;
        if (map.containsKey(item)) {
            if (map.get(item) != null){
                currQuantity = map.get(item);
            }
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
     */
    public void remove(MockItem item, int quantity) {
        //MockItem toReturn =
        // new MockItem(item.getItemType(), item.getBuyingPrice(), item.getSellingPrice());
        int currQuantity = 0;
        if (map.containsKey(item.getItemType())) {
            if (map.get(item.getItemType()) != null) {
                currQuantity = map.get(item.getItemType());
            }
        } else {
            return;
        }
        if ((currQuantity - quantity) < 0) {
            return;
        } else if ((currQuantity - quantity) == 0) {
            map.remove(item.getItemType());
            return;
        } else {
            map.put(item.getItemType(), currQuantity - quantity);
            return;
            //return toReturn;
        }

    }

    /**
     *
     * @param item item to remove
     * @param quantity amount to remove by
     */
    public void  remove(Item item, int quantity) {
        remove(new MockItem(item, 0,0), quantity);
    }

    /**
     *
     * @param i index
     * @return true or false
     */
    public boolean contains(MockItem i) {
        return map.containsKey(i.getItemType());
    }

    /**
     *
     * @param i index
     * @return true or false
     */
    public boolean contains(Item i) { return map.containsKey(i); }

    /**
     *
     * @param i item to addd
     * @return amount of item in cargo
     */
    public int getQuantity(MockItem i) {
        return getQuantity(i.getItemType());
    }

    /**
     *
     * @param item item
     * @return amount
     */
    public Integer getQuantity(Item item) {
        if (contains(item)) {
            return map.get(item);
        }
        return 0;
    }

    /**
     *
     * @return map
     */
    public HashMap<Item, Integer> getMap() {
        return map;
    }
}
