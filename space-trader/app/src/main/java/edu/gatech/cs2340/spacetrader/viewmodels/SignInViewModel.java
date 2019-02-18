package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;


public class SignInViewModel extends AndroidViewModel {
    private PlayerInteractor interactor;

    public SignInViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
    }

    public void addPlayer(Player player) {
        interactor.addPlayerToBackEnd(player);
    }

    //Helper method to validate that the player has the correct amount of skill points allocated
    public void validatePlayer(Player player) { //implementation for paul
    }
}
