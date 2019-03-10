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
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;

public class MarketListViewModel extends AndroidViewModel {
    private MarketInteractor interactor;

    public MarketListViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getMarketInteractor();
    }

    public HashMap<Item, Pair<Integer, Double>> getItems() {
        return interactor.getSeller().getItemsForSale();
    }

    public List<MockItem> getMockItems() {
        return interactor.getAllItems();
    }

    public List<MockItem> getCargoItems() {
        return interactor.getCargoItems();
    }

    public void buyMockItem(MockItem item) {
        interactor.buyMockItem(item);
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
}
