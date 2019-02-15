package edu.gatech.cs2340.spacetrader.model;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Player;

public class Repository {
    private static int next_id = 1;

    private static int getNextUniqueID() {
        return next_id++;
    }

    /** all the Players known in the application */
    private List<Player> allPlayers;

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
    }

}
