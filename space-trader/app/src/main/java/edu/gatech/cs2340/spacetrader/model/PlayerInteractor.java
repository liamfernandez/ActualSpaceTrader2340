package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.Universe;

/**
 *
 */
public class PlayerInteractor extends Interactor {

    /**
     *
     * @param repo repository
     */
    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    /**
     *
     * @return player
     */
    public Player getPlayer() {
        Repository r = getRepository();
        return r.getPlayer();
    }

    /**
     *
     * @param p player
     */
    public void addPlayerToBackEnd (Player p) {
        Repository r = getRepository();
        r.addPlayer(p);

    }

    /**
     * Returns player object based on data in the back end.
     * Make sure to check if player exists before calling
     *
     * Returns null if player doesn't exist
     *
     * @param name Name of player to get from back end
     * @return The player object with data loaded
     */
    public Player loadExistingPlayer(String name) {
        try {
            Repository r = getRepository();
            return r.downloadPlayer(name);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Checks if a player exists in the database
     *
     * @param name Name of player to check
     * @return true if the player exists
     */
    public boolean doesPlayerNameExist(String name) {
        Repository r = getRepository();
        return r.doesPlayerExist(name);
    }

    /**
     *
     */
    public void createUniverse() {
        Universe newUniverse = new Universe();
        Repository r = getRepository();
        r.addUniverse(newUniverse);
    }

    /**
     *
     */
    public void updateExistingPlayer() {
        Repository r = getRepository();
        r.updateExistingPlayer();
    }

    /**
     *
     */
    public void createMockItems() {
        Repository r = getRepository();
        r.initMockItems();
    }

    /**
     *
     */
    public void setSeller() {
        Repository r = getRepository();
        r.setSeller();
    }
}
