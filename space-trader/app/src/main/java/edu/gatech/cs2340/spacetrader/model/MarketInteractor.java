package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.MockItem;

public class MarketInteractor extends Interactor {

    public MarketInteractor(Repository repo) {
        super(repo);
    }

    public DisplayableSeller getSeller() {
        return getRepository().getSeller();
    }

    /**
     * lets the player buy the item item
     * @param item the item the player is buying
     */
    public void buyItem(Item item) {
        getRepository().buyItem(item, 1);
    }

    public boolean buyMockItem(MockItem item) {
        return getRepository().buyMockItem(item);
    }

    public boolean sellMockItem(MockItem item) {
        return getRepository().sellMockItem(item);
    }

    /**
     * sells the item from the players inventory
     * @param item the item to sell
     */
    public void sellItem(Item item) {
        getRepository().sellItem(item, 1);
    }

    public List<MockItem> getAllItems() {
        return getRepository().getAllItems();
    }

    public void refreshMockItems() {
        getRepository().loadItems();
    }

    public List<MockItem> getCargoItems() {
        return getRepository().getCargoList();
    }

    public int getPlayerCredit() {
        return getRepository().getPlayerCredit();
    }
}
