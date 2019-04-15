package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Pair;

import java.util.HashMap;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.MockItem;
import edu.gatech.cs2340.spacetrader.model.MarketInteractor;
import edu.gatech.cs2340.spacetrader.model.Model;

/**
 *
 */
public class MarketListViewModel extends AndroidViewModel {
    private final MarketInteractor interactor;

    /**
     * The constructor ya dig
     * @param application the app
     */
    public MarketListViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getMarketInteractor();
    }

    /**
     * Getting items
     * @return the map of items
     */
    public HashMap<Item, Pair<Integer, Double>> getItems() {
        return interactor.getSeller().getItemsForSale();
    }

    /**
     * gets mock items
     * @return the items
     */
    public List<MockItem> getMockItems() {
        return interactor.getAllItems();
    }

    /**
     * get cargo
     * @return the list
     */
    public List<MockItem> getCargoItems() {
        return interactor.getCargoItems();
    }

    /**
     * buying items
     * @param item the item to buy
     * @return whether it was bought
     */
    public boolean buyMockItem(MockItem item) {
        return interactor.buyMockItem(item);
    }

    /**
     *  sell
     * @param item item sold
     * @return true
     */
    public boolean sellMockItem(MockItem item) {
        return interactor.sellMockItem(item);
    }

    /**
     * lets player buy an item
     * @param item the item the player is buying
     */
    public void buyItem(Item item) {
        interactor.buyItem(item);
    }

    /**
     * lets the player sell an item
     * @param item the item the player is selling
     */
    public void sellItem(Item item) {
        interactor.sellItem(item);
    }

    /**
     * getter
     * @return the credit
     */
    public String getCredit() {
        return "    " + interactor.getPlayerCredit() + "$$";
    }

    /**
     * refreshes items
     */
    public void refreshMockItems() {
        interactor.refreshMockItems();
    }
}
