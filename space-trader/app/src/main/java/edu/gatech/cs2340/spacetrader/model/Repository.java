package edu.gatech.cs2340.spacetrader.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import edu.gatech.cs2340.spacetrader.entity.Inventory;
import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.MockItem;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;
import edu.gatech.cs2340.spacetrader.entity.Universe;

import com.BoardiesITSolutions.AndroidMySQLConnector.Exceptions.SQLColumnNotFoundException;
import com.BoardiesITSolutions.AndroidMySQLConnector.MySQLRow;
import com.BoardiesITSolutions.AndroidMySQLConnector.ResultSet;

/**
 * keeps track of distances and fuel
 */
public class Repository {
    private static int next_id = 1;

    /**
     * looks for next id
     * @return the id
     */
    private static int getNextUniqueID() {
        int temp = next_id;
        next_id++;
        return temp;
    }

    /** all the Players known in the application */
    private Player player;
    private Universe universe;
    private DisplayableSeller seller;

    private List<MockItem> allGameItems;
    private List<MockItem> allItems;
    private List<MockItem> cargoList;

    /**
     * the exception when a player does not exist
     */
    public class PlayerNotFoundException extends Exception {

        PlayerNotFoundException(String message) {
            super(message);
        }

    }

    /**
     * creates an instance of the repository class
     */
    public Repository() {
        allItems = new ArrayList<>();
        cargoList = new ArrayList<>();
        allGameItems = new ArrayList<>();
        loadItems();
    }

    /**
     * Overloaded constructor made by Liam Solely for J unit purposes
     * @param p
     */
    public Repository(Player p, List<MockItem> improvCargo) {
        allItems = new ArrayList<>();
        cargoList = improvCargo;
        allGameItems = new ArrayList<>();
        player = p;
        //loadItems();
    }

    /**
     * upload a new player
     * @param p the player to upload
     */
    public void uploadNewPlayer(Player p) {
        String query = "INSERT INTO players (credit, difficulty, currPlanet, skill_fighter, " +
                "skill_trader, skill_pilot, skill_engineer, name, inventory, fuel, currSystem) \n" +
                "values (";
        query += p.getCredit() + ", ";
        query += 0 + ", ";
        Planet planet = p.getCurrPlanet();
        query += "'" + planet.getName() + "', ";
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
        SolarSystem s = p.getCurrSolarSystem();
        query += "'" + s.getName() + "'";
        query += ")";
        MySQLTalker.executeNonReturningQuery(query);
    }


    /**
     * downloads the player
     * @param name the name of the player
     * @return the player to download
     * @throws PlayerNotFoundException if the player does not exist or cannot be found
     */
    public Player downloadPlayer(String name) throws PlayerNotFoundException {
        String query = "SELECT * FROM players WHERE name = '";
        query += name;
        query += "'";
        MySQLTalker.executeReturningQuery(query);
        int loopCounter = 0;
        while ((MySQLTalker.isQueryInProgress()) && (loopCounter < 20)) {
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
            //System.out.println(ex);
            p = null;
        }
        player = p;
        return p;
    }

    /**
     * Updates database player to match Player object
     * This method should only be called if it's
     * known that the player exists in the database
     *
     */
    public void updateExistingPlayer() {
        Player p = player;
        String query = "UPDATE players SET ";
        String credit = p.getCredit() + "";
        String difficulty = "0";
        Planet planet1 = p.getCurrPlanet();
        String planet = planet1.getName();
        String skill1 = p.getSkill1() + "";
        String skill2 = p.getSkill2() + "";
        String skill3 = p.getSkill3() + "";
        String skill4 = p.getSkill4() + "";
        String items = "";
        for (MockItem curr : cargoList) {
            items += "<" + curr.getName() + ">";
        }
        String fuel = p.getFuel() + "";
        SolarSystem s = p.getCurrSolarSystem();
        String system = s.getName();
        query += "credit = " + credit + ", "
                + "difficulty = " + difficulty + ", "
                + "currPlanet = '" + planet + "', "
                + "currSystem = '" + system + "', "
                + "skill_fighter = " + skill1 + ", "
                + "skill_trader = " + skill2 + ", "
                + "skill_pilot = " + skill3 + ", "
                + "skill_engineer = " + skill4 + ", "
                + "inventory = '" + items + "', "
                + "fuel = " + fuel + " ";
        query += "WHERE name = '" + p.getName() + "';";
        System.out.println(query);
        MySQLTalker.executeNonReturningQuery(query);
    }

    /**
     *
     * @param player player
     * @return true or false
     */
    public boolean doesPlayerExist(String player) {
        try {
            downloadPlayer(player);
            return true;
        } catch (PlayerNotFoundException p) {
            return false;
        }
    }

    /**
     * loads the cargo list
     * @param inventory the string inventory
     */
    public void loadTheCargoList(String inventory) {
        int index;
        HashMap<String, Item> items = new HashMap<>();
        for (Item i : Item.values()) {
            items.put(i.getName(), i);
        }

        while ((inventory != null) && ((inventory.indexOf('<')) != -1)) {
            index = inventory.indexOf('<');
            inventory = inventory.substring(index + 1);
            int endIndex = inventory.indexOf('>');
            String currMockItem = inventory.substring(0, endIndex);
            MockItem item = new MockItem(items.get(currMockItem), 0, 0);
            //inventory = inventory.substring(endIndex);
            cargoList.add(item);
        }
    }

    /**
     * sets the player's location based on string planet and system names
     * @param systemName the name of solar system
     * @param planetName the name of  planet
     * @param player the player to set location
     */
    private void setPlayerLocation(String systemName, String planetName, Player player) {
        HashMap<String, SolarSystem> systems =  new HashMap<>();
        for (SolarSystem s : universe.getAllSystems()) {
            systems.put(s.getName(), s);
        }
        SolarSystem currSystem = systems.get(systemName);
        HashMap<String, Planet> planets = new HashMap<>();
        if (currSystem != null) {
            for (Planet p : currSystem.getPlanets()) {
                planets.put(p.getName(), p);
            }
        }
        Planet currPlanet = planets.get(planetName);
        player.setCurrSolarSystem(currSystem);
        player.setCurrPlanet(currPlanet);
    }

    /**
     * loads items
     */
    public void loadItems() {
        allItems = new ArrayList<>();
        List<MockItem> updatedCargo = new ArrayList<>();
        //Log.d("att sad", "starting the loading of items");
        //Log.d("market", "allGameItems" + allGameItems.toString());
        for (MockItem mockItem : allGameItems) {
            //Log.d("loading", "testing if " + mockItem.getName() + "available on "
                    //+ player.getCurrPlanet());
            if (mockItem.isSellable(player.getCurrPlanet())) {
                //Log.d("loading", "adding " + mockItem.getName() + "KKKKKKKKKK");
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

    /**
     * loads the cargo
     * @param item item to load to cargo
     */
    public void loadCargo(MockItem item) {
        addCargo(item);
    }

    /**
     * removes a cargo item from list
     * @param item items to remove
     */
    public void removeCargo(MockItem item) {
        cargoList.remove(item);
    }

    /**
     * adds a cargo to cargo list
     * @param item the item to add
     */
    public void addCargo(MockItem item) {
        cargoList.add(item);
    }

    /**
     * adds an item
     * @param item item to add
     */
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

    /**
     * gets the list of cargo
     * @return the list of items in cargo
     */
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
        addPlayerTestable(p, true);
        uploadNewPlayer(player);
        //Log.d("APP", "Interactor: added player: " + p);
    }

    public void addPlayerTestable(Player p, boolean useDatabase) {
        p.setCurrPlanet(universe.getStartingPlanet());
        p.setCurrSolarSystem(universe.getStartingSolarSystem());
        player = p;
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
     * @return displayableSeller
     */
    public DisplayableSeller getSeller() {
        return seller;
    }

    /**
     * buys the mock items
     * @param item the item to buy
     * @return true if it worked or not
     */
    public boolean buyMockItem(MockItem item) {
        if ((cargoList.size() < player.getMaxItems())
                && (player.getCredit() > item.getSellingPrice())){
            //Log.d("buyItem", "basePrice: " + item.getBasePrice() + ", buyPrice: "
                    //+ item.getBuyingPrice() + ", sellPrice: " + item.getSellingPrice());
            player.editCredit(0 - item.getBuyingPrice());
            for (MockItem mockitem : cargoList) {
                if (cargoList.contains(item)) {
                    MockItem prevItem = cargoList.get(cargoList.indexOf(mockitem));
                    item.setSellingPrice(prevItem.getSellingPrice());
                }
            }
            cargoList.add(new MockItem(item));
            //Log.d("BUUUUUYYYYYYYYYYYY", "true");
            return true;
        }
        //Log.d("BBBBUUYUYUUYUYUY", "" + player.getCredit());
        //Log.d("BUUUUYYYYYYYYY", "false");
        return false;
    }

    /**
     * sells items
     * @param item the item to sell
     * @return true if it works
     */
    public void sellMockItem(MockItem item) {
        cargoList.remove(item);
        player.editCredit(item.getSellingPrice());
        //Log.d("SEEEELLLLLLLLLELLEL", "" + player.getCredit());
    }

    /**
     * gets a mock item at random
     * @return the mock item that is random
     */
    public MockItem getRandomItem() {
        if (cargoList.isEmpty()) {
            return null;
        }
        Random rand = new Random();
        int random = rand.nextInt(cargoList.size());
        return cargoList.get(random);
    }

    /**
     * initializes the list of mock items
     */
    public void initMockItems() {
        allGameItems = universe.initMockItems();
    }

    /**
     * gets the player's money
     * @return the amount of money the player has
     */
    public int getPlayerCredit() {
        return (int) player.getCredit();
    }

    /**
     * gets the universe
     * @return universe
     */
    public Universe getUniverse() {
        return universe;
    }

    /**
     * gets the player's fuel
     * @return the amount of fuel
     */
    public double getPlayerFuel() {
        return player.getFuel();
    }

    /**
     * SOLELY for Liam J unit purposes
     * @param item the item to buy
     * @return true if it worked or not
     */
    public boolean buyMockItem(MockItem item, int i) {
        if ((cargoList.size() < player.getMaxItems())
                && (player.getCredit() > item.getSellingPrice())){
            //Log.d("buyItem", "basePrice: " + item.getBasePrice() + ", buyPrice: "
            //        + item.getBuyingPrice() + ", sellPrice: " + item.getSellingPrice());
            player.editCredit(0 - item.getBuyingPrice());
            for (MockItem mockitem : cargoList) {
                if (cargoList.contains(item)) {
                    MockItem prevItem = cargoList.get(cargoList.indexOf(mockitem));
                    item.setSellingPrice(prevItem.getSellingPrice());
                }
            }
            cargoList.add(new MockItem(item));
            //Log.d("BUUUUUYYYYYYYYYYYY", "true");
            return true;
        }
        //Log.d("BBBBUUYUYUUYUYUY", "" + player.getCredit());
        //Log.d("BUUUUYYYYYYYYY", "false");
        return false;
    }

}
