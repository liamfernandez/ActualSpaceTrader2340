package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;

/**
 * adapts the sign in from the model to the view
 */
public class SignInViewModel extends AndroidViewModel {
    private final PlayerInteractor interactor;

    /**
     * creates an instance of the class
     * @param application the application to assign the assistance to
     */
    public SignInViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
    }

    /**
     * adds a player to the game
     * @param player the player to add to the game
     * @return the string version of the player
     */
    public String addPlayer(Player player) {
        interactor.createUniverse();
        interactor.createMockItems();
        boolean validPoints = validatePlayer(player);
        boolean validName = validatePlayerName(player);
        if (validPoints && validName) {
            interactor.addPlayerToBackEnd(player);
            interactor.setSeller();
            return "Player Created";
        } else if (validName) {
            return "Need 16 total skill points.";
        } else if (validPoints) {
            return "Need to input a unique name!";
        } else {
            return "Need to input a name as well as assign 16 skill points.";
        }
    }

    private boolean validatePlayer(Player player) {
        if (player == null) {
            throw new NullPointerException("Player is null");
        }
        int sumSkills = 0;
        sumSkills += player.getSkill1();
        sumSkills += player.getSkill2();
        sumSkills += player.getSkill3();
        sumSkills += player.getSkill4();
        if (sumSkills == 16) {
            return true;
        }
        return false;
    }

    /**
     * lauren made this to be able to test the private method above
     * @param p player to test
     * @return true or false if it is valid or not
     */
    public boolean validateTest(Player p) {
        return validatePlayer(p);
    }

    public boolean validatePlayerName(Player player) {
        return (!(player.getName().equals(""))
                && !(interactor.doesPlayerNameExist(player.getName()))
        );
    }

    public boolean doesPlayerNameExist(String playerName) {
        interactor.createUniverse();
        return interactor.doesPlayerNameExist(playerName);
    }

    public Player loadExistingPlayer(String playerName) {
        return interactor.loadExistingPlayer(playerName);
    }
}