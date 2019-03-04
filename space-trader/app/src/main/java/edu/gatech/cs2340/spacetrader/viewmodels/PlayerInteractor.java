package edu.gatech.cs2340.spacetrader.viewmodels;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.model.Interactor;
import edu.gatech.cs2340.spacetrader.model.Repository;

public class PlayerInteractor extends Interactor {

    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    public List<Player> getAllStudents() {
        return getRepository().getAllPlayers();
    }

    public void addPlayerToBackEnd (Player p) {
        getRepository().addPlayer(p);
    }

}
