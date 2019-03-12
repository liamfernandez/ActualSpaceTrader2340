package edu.gatech.cs2340.spacetrader.model;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.cs2340.spacetrader.entity.Inventory;
import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.MockItem;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;

public class Repository {
    private static int next_id = 1;

    private static int getNextUniqueID() {
        return next_id++;
    }

    /** all the Players known in the application */
    private Player player;
    private Universe universe;
    private DisplayableSeller seller;

    private List<MockItem> allGameItems;
    private List<MockItem> allItems;
    private List<MockItem> cargoList;

    public Repository() {
        allItems = new ArrayList<>();
        cargoList = new ArrayList<>();
        allGameItems = new ArrayList<>();
        loadItems();
    }

    public void loadItems() {
        allItems = new ArrayList<>();
        List<MockItem> updatedCargo = new ArrayList<>();
        Log.d("att sad", "starting the loading of items");
        Log.d("market", "allGameItems" + allGameItems.toString());
        for (MockItem mockItem : allGameItems) {
            Log.d("loading", "testing if " + mockItem.getName() + "available on " + player.getCurrPlanet());
            if (mockItem.isSellable(player.getCurrPlanet())) {
                Log.d("loading", "adding " + mockItem.getName() + "KKKKKKKKKK");
                MockItem marketMockItem = mockItem;
                marketMockItem.setBuyingPrice(mockItem.calcBuyingPrice(player.getCurrPlanet()));
                marketMockItem.setSellingPrice(mockItem.calcSellingPrice());
                addItem(marketMockItem);
            }
        }
        for (MockItem item : cargoList) {
            if (updatedCargo.contains(item)) {
                MockItem prevItem = updatedCargo.get(updatedCargo.indexOf(item));
                item.setSellingPrice(prevItem.getSellingPrice());
            } else {
                item.setSellingPrice(item.calcSellingPrice());
            }
            updatedCargo.add(item);
        }
        cargoList = updatedCargo;
    }

    public void loadCargo(MockItem item) {
        addCargo(item);
    }

    public void addCargo(MockItem item) {
        cargoList.add(item);
    }

    public void addItem(MockItem item) {
        allItems.add(item);
    }

    /**
     * get all the lists in the system
     * @return list of all the items
     */
    public List<MockItem> getAllItems() {
        return allItems;
    }

    public List<MockItem> getCargoList() {
        return cargoList;
    }

    /**
     * get all the players
     * @return list of all students
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * add a player to the game
     * @param p the player to add
     */
    public void addPlayer(Player p) {
        p.setId(Repository.getNextUniqueID());
        p.setCurrPlanet(universe.getStartingPlanet());
        player = p;
        Log.d("APP", "Interactor: added player: " + p);

    }

    /**
     * adds the universe to the game
     * @param newUniverse the universe to add
     */
    public void addUniverse(Universe newUniverse) {
        universe = newUniverse;
        initMockItems();
    }

    /**
     * adds the original seller to the game
     */
    public void setSeller() {
        seller = new MarketSeller(player.getCurrPlanet());
    }

    /**
     * gets the current Displayable seller
     */
    public DisplayableSeller getSeller() {
        return seller;
    }

    /**
     * Method to make player purchase an item.
     * Will confirm the player has sufficient funds, and
     * the seller actually has enough in stock. Purchase executed by:
     * 1) Removing item(s) from seller
     * 2) Adding item(s) to player inventory
     * 3) Adjusting player credit accordingly
     *
     * If seller has insufficient stock, sale will not
     * go through.
     *
     * @param item Item they are purchasing
     * @param quantity quantity that the player is buying
     * @return true if purchase executed, false if not
     */
    public boolean buyItem(Item item, int quantity) {
        if (seller.getQuantityForSale(item) >= quantity
                && seller.getPrice(item) * quantity <= player.getCredit()) {
            for (int i = 0; i < quantity; i++) {
                seller.remove(item);
            }
            player.getInventory().add(item, quantity);
            player.editCredit(-1 * seller.getPrice(item) * quantity);
            return true;
        } else {
            return false;
        }
    }

    public boolean buyMockItem(MockItem item) {
        if (cargoList.size() < player.getMaxItems() && player.getCredit() > item.getSellingPrice()) {
            Log.d("buyItem", "basePrice: " + item.getBasePrice() + ", buyPrice: " + item.getBuyingPrice() + ", sellPrice: " + item.getSellingPrice());
            player.editCredit(0 - item.getBuyingPrice());
            for (MockItem mockitem : cargoList) {
                if (cargoList.contains(item)) {
                    MockItem prevItem = cargoList.get(cargoList.indexOf(mockitem));
                    item.setSellingPrice(prevItem.getSellingPrice());
                }
            }
            cargoList.add(new MockItem(item));
            Log.d("BUUUUUYYYYYYYYYYYY", "true");
            return true;
        }
        Log.d("BBBBUUYUYUUYUYUY", "" + player.getCredit());
        Log.d("BUUUUYYYYYYYYY", "false");
        return false;
    }

    public boolean sellMockItem(MockItem item) {
        cargoList.remove(item);
        player.editCredit(item.getSellingPrice());
        Log.d("SEEEELLLLLLLLLELLEL", "" + player.getCredit());
        return true;
    }

    /**
     * Method to make a player sell and item. Will first check
     * if they're able to, and if so, execute the sale by
     * 1) removing item(s) from players inventory
     * 2) adding the value of them to player's price
     *
     * Players are unable to sell if MLTP of item isn't good
     * or if they have insufficient quantity
     *
     * @param item Item to be sold
     * @param quantity Quantity of Item to be sold
     * @return true if sale executed, false if sale invalid
     */

    public boolean sellItem(Item item, int quantity) {
        Planet planet = player.getCurrPlanet();
        if (planet != null && planet.getTechLevel().getLevel() < item.getMTLP()) {
            return false;
        }
        Inventory inventory = player.getInventory();
        if (!inventory.contains(item)) {
            return false;
        } else if (inventory.getQuantity(item) < quantity) {
            return false;
        } else {
            inventory.remove(item, quantity);
            if (planet == null) {
                player.editCredit(Store.getSpaceTradePrice(item));
                return true;
            } else {
                player.editCredit(Store.getMarketPrice(item, planet) * quantity);
                return true;
            }
        }
    }

    public void initMockItems() {
        allGameItems = universe.initMockItems();
    }

    public int getPlayerCredit() {
        return (int) player.getCredit();
    }
}
