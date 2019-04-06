package edu.gatech.cs2340.spacetrader.model;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import edu.gatech.cs2340.spacetrader.entity.Inventory;
import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.MockItem;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;

import com.BoardiesITSolutions.AndroidMySQLConnector.Exceptions.SQLColumnNotFoundException;
import com.BoardiesITSolutions.AndroidMySQLConnector.MySQLRow;
import com.BoardiesITSolutions.AndroidMySQLConnector.ResultSet;


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

    private class PlayerNotFoundException extends Exception {

        PlayerNotFoundException(String message) {
            super(message);
        }

    }

    public Repository() {
        allItems = new ArrayList<>();
        cargoList = new ArrayList<>();
        allGameItems = new ArrayList<>();
        loadItems();
    }

    public void uploadNewPlayer(Player p) {
        String query = "INSERT INTO players (credit, difficulty, currPlanet, skill_fighter, skill_trader, skill_pilot, skill_engineer, name, inventory, fuel, currSystem) \n" +
                "values (";
        query += p.getCredit() + ", ";
        query += 0 + ", ";
        query += "'" + p.getCurrPlanet().getName() + "', ";
        query += p.getSkill1() + ", ";
        query += p.getSkill2() + ", ";
        query += p.getSkill3() + ", ";
        query += p.getSkill4() + ", ";
        query += "'" + p.getName() + "',";
        query += "'";
        for (MockItem curr : cargoList) {
            query += "<" + curr.getName() + ">";
        }
        query += "', ";
        query += p.getFuel() + ", ";
        query += "'" + p.getCurrSolarSystem().getName() + "'";
        query += ")";
        MySQLTalker.executeNonReturningQuery(query);
    }

    public void updateExistingPlayer(Player p) {

    }

    public Player downloadPlayer(String name) throws PlayerNotFoundException {
        String query = "SELECT * FROM players WHERE name = '";
        query += name;
        query += "'";
        MySQLTalker.executeReturningQuery(query);
        int loopCounter = 0;
        while (MySQLTalker.isQueryInProgress() && loopCounter < 20) {
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
                System.out.println("Sleep failed!");
            }
            loopCounter++;
        }
        ResultSet resultSet = MySQLTalker.getResultSet();

        MySQLRow mySQLRow = resultSet.getNextRow();
        if (mySQLRow == null) {
            throw new PlayerNotFoundException("Player " + name + " does not exist!");
        }

        Player p;
        try {
            double credit = mySQLRow.getDouble("credit");
            int difficulty = mySQLRow.getInt("difficulty");
            String currPlanet = mySQLRow.getString("currPlanet");
            String currSystem = mySQLRow.getString("currSystem");
            int skill1 = mySQLRow.getInt("skill_fighter");
            int skill2 = mySQLRow.getInt("skill_trader");
            int skill3 = mySQLRow.getInt("skill_pilot");
            int skill4 = mySQLRow.getInt("skill_engineer");
            String inventoryString = mySQLRow.getString("inventory");
            loadTheCargoList(inventoryString);
            double fuel = mySQLRow.getDouble("fuel");
            p = new Player(name, skill1, skill2, skill3, skill4);
            p.setCredit(credit);
            p.setFuel(credit);
            setPlayerLocation(currSystem, currPlanet, p);
        } catch (SQLColumnNotFoundException ex) {
            System.out.println(ex);
            p = null;
        }
        return p;
    }

    private void loadTheCargoList(String inventory) {
        int index;
        HashMap<String, Item> items = new HashMap<String, Item>();
        for (Item i : Item.values()) {
            items.put(i.getName(), i);
        }

        while ((index = inventory.indexOf('<')) != -1) {
            inventory = inventory.substring(index + 1);
            int endIndex = inventory.indexOf('>');
            String currMockItem = inventory.substring(0, endIndex);
            MockItem item = new MockItem(items.get(currMockItem), 0, 0);
            inventory = inventory.substring(endIndex);
            cargoList.add(item);
        }
    }

    private void setPlayerLocation(String systemName, String planetName, Player player) {
        HashMap<String, SolarSystem> systems =  new HashMap<>();
        for (SolarSystem s : universe.getAllSystems()) {
            systems.put(s.getName(), s);
        }
        SolarSystem currSystem = systems.get(systemName);
        HashMap<String, Planet> planets = new HashMap<>();
        for (Planet p : currSystem.getPlanets()) {
            planets.put(p.getName(), p);
        }
        Planet currPlanet = planets.get(planetName);
        player.setCurrSolarSystem(currSystem);
        player.setCurrPlanet(currPlanet);
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

    public void removeCargo(MockItem item) {
        cargoList.remove(item);
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
     * @return player
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
        uploadNewPlayer(player);
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

    public MockItem getRandomItem() {
        if (cargoList.size() == 0) {
            return null;
        }
        int random = new Random().nextInt(cargoList.size());
        return cargoList.get(random);
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

    public Universe getUniverse() {
        return universe;
    }

    public double getPlayerFuel() {
        return player.getFuel();
    }
}
