package edu.gatech.cs2340.spacetrader.model;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.cs2340.spacetrader.entity.Item;
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

    public Repository() {
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
    }

    /**
     * adds the original seller to the game
     */
    public void addSeller() {
        seller = new MarketSeller(player.getCurrPlanet());
    }

    /**
     * gets the current Displayable seller
     */
    public DisplayableSeller getSeller() {
        return seller;
    }

    /**
     * removes the item from the sellers stock
     * @param item the item to remove from stock
     */
    public void buyItem(Item item) {
        seller.remove(item);
        player.addItem(item);
    }
}
