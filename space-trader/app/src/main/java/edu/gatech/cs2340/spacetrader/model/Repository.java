package edu.gatech.cs2340.spacetrader.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<Player> allPlayers;
    private Universe universe;

    public Repository() {
        allPlayers = new ArrayList<>();
    }

    /**
     * get all the players
     * @return list of all students
     */
    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    /**
     * add a player to the game
     * @param p the player to add
     */
    public void addPlayer(Player p) {
        p.setId(Repository.getNextUniqueID());
        allPlayers.add(p);
        Log.d("APP", "Interactor: added player: " + p);

    }

    /**
     * adds the universe to the game
     * @param newUniverse the universe to add
     */
    public void addUniverse(Universe newUniverse) {
        universe = newUniverse;
    }
}
