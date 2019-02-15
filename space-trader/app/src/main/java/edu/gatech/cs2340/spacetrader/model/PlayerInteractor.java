package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Player;

public class PlayerInteractor extends Interactor {

    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    public List<Player> getAllStudents() {
        return getRepository().getAllPlayers();
    }

    public void addStudent (Player p) {
        getRepository().addPlayer(p);
    }

}
