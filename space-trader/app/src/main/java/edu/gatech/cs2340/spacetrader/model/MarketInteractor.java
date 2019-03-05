package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Item;

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
        getRepository().buyItem(item);
    }
}
