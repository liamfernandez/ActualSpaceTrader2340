package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Pair;

import java.util.HashMap;

import edu.gatech.cs2340.spacetrader.entity.Item;
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
}
