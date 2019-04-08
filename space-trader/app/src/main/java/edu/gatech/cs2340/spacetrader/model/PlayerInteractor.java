package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.Universe;

public class PlayerInteractor extends Interactor {

    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    public Player getPlayer() {
        return getRepository().getPlayer();
    }

    public void addPlayerToBackEnd (Player p) {
        getRepository().addPlayer(p);

    }

    /**
     * Returns player object based on
     * data in the back end
     *
     * @param name Name of player to get from back end
     * @return The player object with data loaded
     */
    public Player loadExistingPlayer(String name) {
        try {
            return getRepository().downloadPlayer(name);
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean doesPlayerNameExist(String name) {
        return getRepository().doesPlayerExist(name);
    }

    public void createUniverse() {
        Universe newUniverse = new Universe();
        getRepository().addUniverse(newUniverse);
    }

    public void createMockItems() {
        getRepository().initMockItems();
    }

    public void setSeller() {
        getRepository().setSeller();
    }
}
