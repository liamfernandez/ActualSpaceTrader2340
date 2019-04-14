package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.MockItem;

/**
 *
 */
public class MarketInteractor extends Interactor {

    /**
     *
     * @param repo repository
     */
    public MarketInteractor(Repository repo) {
        super(repo);
    }

    /**
     *
     * @return displayableseller
     */
    public DisplayableSeller getSeller() {
        Repository r = getRepository();
        return r.getSeller();
    }

    /**
     * lets the player buy the item item
     * @param item the item the player is buying
     */
    public void buyItem(Item item) {
        Repository r = getRepository();
        r.buyItem(item, 1);
    }

    /**
     *
     * @param item item
     * @return true or false
     */
    public boolean buyMockItem(MockItem item) {
        Repository r = getRepository();
        return r.buyMockItem(item);
    }

    /**
     *
     * @param item item
     * @return true or false
     */
    public void sellMockItem(MockItem item) {
        Repository r = getRepository();
        r.sellMockItem(item);
    }

    /**
     * sells the item from the players inventory
     * @param item the item to sell
     */
    public void sellItem(Item item) {
        Repository r = getRepository();
        r.sellItem(item, 1);
    }

    /**
     *
     * @return list of mockItems
     */
    public List<MockItem> getAllItems() {
        Repository r = getRepository();
        return r.getAllItems();
    }

    /**
     *
     */
    public void refreshMockItems() {
        Repository r = getRepository();
        r.loadItems();
    }

    /**
     *
     * @return list of mock items
     */
    public List<MockItem> getCargoItems() {
        Repository r = getRepository();
        return r.getCargoList();
    }

    /**
     *
     * @return credit
     */
    public int getPlayerCredit() {
        Repository r = getRepository();
        return r.getPlayerCredit();
    }
}
